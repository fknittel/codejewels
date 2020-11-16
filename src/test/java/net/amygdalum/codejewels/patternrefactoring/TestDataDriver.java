package net.amygdalum.codejewels.patternrefactoring;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import net.amygdalum.codejewels.patternrefactoring.AuthenticationService;
import net.amygdalum.codejewels.patternrefactoring.BusinessService;
import net.amygdalum.codejewels.patternrefactoring.DBRequest;
import net.amygdalum.codejewels.patternrefactoring.DBService;
import net.amygdalum.codejewels.patternrefactoring.SerializationService;
import net.amygdalum.codejewels.patternrefactoring.impl.FakeAuthenticationService;
import net.amygdalum.codejewels.patternrefactoring.impl.FakeBusinessService;
import net.amygdalum.codejewels.patternrefactoring.impl.FakeSerializationService;
import net.amygdalum.testrecorder.DefaultSerializationProfile;
import net.amygdalum.testrecorder.callsiterecorder.CallsiteRecorder;
import net.amygdalum.testrecorder.configurator.AgentConfigurator;
import net.amygdalum.testrecorder.deserializers.Adaptors;
import net.amygdalum.testrecorder.deserializers.DeserializerFactory;
import net.amygdalum.testrecorder.deserializers.DeserializerTypeManager;
import net.amygdalum.testrecorder.deserializers.builder.DefaultSetupGenerators;
import net.amygdalum.testrecorder.deserializers.builder.SetupGenerators;
import net.amygdalum.testrecorder.deserializers.matcher.DefaultMatcherGenerators;
import net.amygdalum.testrecorder.deserializers.matcher.MatcherGenerators;
import net.amygdalum.testrecorder.generator.DefaultTestGeneratorProfile;
import net.amygdalum.testrecorder.generator.JUnit5TestTemplate;
import net.amygdalum.testrecorder.generator.MethodGenerator;
import net.amygdalum.testrecorder.profile.Classes;
import net.amygdalum.testrecorder.profile.Methods;
import net.amygdalum.testrecorder.runtime.GenericObject;
import net.amygdalum.testrecorder.types.ContextSnapshot;
import net.amygdalum.testrecorder.types.TypeManager;

class TestDataDriver {

    public static List<DBRequest> serviceVersionRequests() {
        return Stream.<GenericObject> of(
            new GenericObject() {
                private String id = "1";
                private String user = "allowed";
                private Map<String, String> attributes = emptyMap();
            },
            new GenericObject() {
                private String id = "2";
                private String user = "denied";
                private Map<String, String> attributes = emptyMap();
            },
            new GenericObject() {
                private String id = "10";
                private String user = "allowed";
                private Map<String, String> attributes = emptyMap();
            },
            new GenericObject() {
                private String id = "100";
                private String user = "allowed";
                private Map<String, String> attributes = emptyMap();
            })
            .map(o -> o.as(DBRequest.class))
            .collect(toList());
    }

    public static List<DBRequest> offerRequests() {
        return Stream.<GenericObject> of(
            new GenericObject() {
                private String id = "1";
                private String user = "allowed";
                private Map<String, String> attributes = map("offerId", "1");
            },
            new GenericObject() {
                private String id = "2";
                private String user = "denied";
                private Map<String, String> attributes = map("offerId", "2");
            },
            new GenericObject() {
                private String id = "3";
                private String user = "allowed";
                private Map<String, String> attributes = map("offerId", "broken");
            },
            new GenericObject() {

                private String id = "10";
                private String user = "allowed";
                private Map<String, String> attributes = map("offerId", "10");
            },
            new GenericObject() {

                private String id = "100";
                private String user = "allowed";
                private Map<String, String> attributes = map("offerId", "100");
            }).map(o -> o.as(DBRequest.class)).collect(toList());
    }

    public static List<DBRequest> positionRequests() {
        return Stream.<GenericObject> of(
            new GenericObject() {
                private String id = "1";
                private String user = "allowed";
                private Map<String, String> attributes = map("offerId", "1", "price", "42.0");
            },
            new GenericObject() {
                private String id = "2";
                private String user = "denied";
                private Map<String, String> attributes = map("offerId", "1", "price", "42.0");
            },
            new GenericObject() {
                private String id = "3";
                private String user = "allowed";
                private Map<String, String> attributes = map("offerId", "2", "price", "21.0");
            },
            new GenericObject() {
                private String id = "4";
                private String user = "allowed";
                private Map<String, String> attributes = map("offerId", "broken", "price", "broken");
            },
            new GenericObject() {
                private String id = "10";
                private String user = "allowed";
                private Map<String, String> attributes = map("offerId", "10", "price", "11.0");
            },
            new GenericObject() {
                private String id = "100";
                private String user = "allowed";
                private Map<String, String> attributes = map("offerId", "100", "price", "11.0");
            })
            .map(o -> o.as(DBRequest.class))
            .collect(toList());
    }

    public static void main(String[] args) throws Exception {
        AgentConfigurator configurator = new AgentConfigurator()
            .defaultSerializers()
            .defaultSetupGenerators()
            .defaultMatcherGenerators()
            .generateTests(DefaultTestGeneratorProfile::new)
            .record(MySerializationProfile::new);
        try (CallsiteRecorder recorder = CallsiteRecorder.create(configurator, new Class[] { DBService.class })) {
            AuthenticationService auth = new FakeAuthenticationService("allowed");
            SerializationService serializer = new FakeSerializationService();
            BusinessService business = new FakeBusinessService();

            printTest(recorder, recorder.record(() -> {
                DBService service = new DBService(auth, serializer, business);
                for (DBRequest req : serviceVersionRequests()) {
                    service.getServiceVersion(req);
                }
                for (DBRequest req : offerRequests()) {
                    service.requestOffer(req);
                }
                for (DBRequest req : positionRequests()) {
                    service.addPosition(req);
                }
            }));
        }

    }

    private static void printTest(CallsiteRecorder recorder, CompletableFuture<List<ContextSnapshot>> recordings) {
        TypeManager types = new DeserializerTypeManager();
        DeserializerFactory setup = new SetupGenerators(new Adaptors().load(DefaultSetupGenerators.defaults()));
        DeserializerFactory matcher = new MatcherGenerators(new Adaptors().load(DefaultMatcherGenerators.defaults()));

        JUnit5TestTemplate junit5 = new JUnit5TestTemplate();

        int[] counter = new int[1];

        recordings.thenAccept(snapshots -> snapshots.stream()
            .forEach(snapshot -> {
                System.out.println(new MethodGenerator(counter[0]++, types, setup, matcher, junit5, emptyList())
                    .analyze(snapshot)
                    .generateArrange()
                    .generateAct()
                    .generateAssert()
                    .generateTest());
            }));
    }

    private static HashMap<String, String> map(String... keyvalues) {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < keyvalues.length - 1; i += 2) {
            map.put(keyvalues[i], keyvalues[i + 1]);
        }
        return map;
    }

    public static class MySerializationProfile extends DefaultSerializationProfile {

        @Override
        public List<Classes> getClasses() {
            return asList(
                Classes.byDescription("com/enbw/eop/duplication/DBService"),
                Classes.byDescription("ch/qos/logback/classic/Logger"));
        }

        @Override
        public List<Methods> getOutputs() {
            return asList(
                Methods.byDescription("ch/qos/logback/classic/Logger", "info", "(Ljava/lang/String;)V"),
                Methods.byDescription("ch/qos/logback/classic/Logger", "warn", "(Ljava/lang/String;)V"));
        }

        @Override
        public List<Methods> getRecorded() {
            return asList(
                Methods.byDescription("com/enbw/eop/duplication/DBService", "getServiceVersion",
                    "(Lcom/enbw/eop/duplication/DBRequest;)Lcom/enbw/eop/duplication/DBResponse;"),
                Methods.byDescription("com/enbw/eop/duplication/DBService", "requestOffer",
                    "(Lcom/enbw/eop/duplication/DBRequest;)Lcom/enbw/eop/duplication/DBResponse;"),
                Methods.byDescription("com/enbw/eop/duplication/DBService", "addPosition",
                    "(Lcom/enbw/eop/duplication/DBRequest;)Lcom/enbw/eop/duplication/DBResponse;"));
        }
    }
}
