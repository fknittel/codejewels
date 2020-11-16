package net.amygdalum.codejewels.patternrefactoring;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static net.amygdalum.testrecorder.runtime.ContainsInOrderMatcher.containsInOrder;
import static net.amygdalum.testrecorder.runtime.MapMatcher.containsEntries;
import static net.amygdalum.testrecorder.runtime.MapMatcher.noEntries;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.amygdalum.codejewels.patternrefactoring.AuthenticationService;
import net.amygdalum.codejewels.patternrefactoring.BusinessService;
import net.amygdalum.codejewels.patternrefactoring.DBRequest;
import net.amygdalum.codejewels.patternrefactoring.DBResponse;
import net.amygdalum.codejewels.patternrefactoring.DBService;
import net.amygdalum.codejewels.patternrefactoring.SerializationService;
import net.amygdalum.codejewels.patternrefactoring.impl.FakeAuthenticationService;
import net.amygdalum.codejewels.patternrefactoring.impl.FakeBusinessService;
import net.amygdalum.codejewels.patternrefactoring.impl.FakeSerializationService;
import net.amygdalum.testrecorder.fakeio.FakeIO;
import net.amygdalum.testrecorder.runtime.Aspect;
import net.amygdalum.testrecorder.runtime.GenericMatcher;
import net.amygdalum.testrecorder.runtime.GenericObject;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class GeneratedTest {

    @Test
    public void testGetServiceVersion0() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("1");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = emptyMap();
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 1")).addFreeVirtual(1208736537, null, equalTo("finished request 1")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.getServiceVersion(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "1";
            String role = "allowed";
            int status = 200;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = noEntries(String.class, String.class);
            String id = "1";
            String user = "allowed";
        }.matching(DBRequest.class));
    }

    @Test
    public void testGetServiceVersion1() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("2");
        dBRequest1.setUser("denied");
        Map<String, String> map1 = emptyMap();
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 2")).addFreeVirtual(1208736537, null, equalTo("finished request 2"))
            .fakeOutput(new Aspect() {
                public void warn(String string1) {
                }
            }).addFreeVirtual(1208736537, null, equalTo("user denied is not authorized")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.getServiceVersion(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "Access denied";
            String role = null;
            int status = 401;
            String user = null;
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = noEntries(String.class, String.class);
            String id = "2";
            String user = "denied";
        }.matching(DBRequest.class));
    }

    @Test
    public void testGetServiceVersion2() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("10");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = emptyMap();
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 10")).addFreeVirtual(1208736537, null, equalTo("finished request 10"))
            .fakeOutput(new Aspect() {
                public void warn(String string1) {
                }
            }).addFreeVirtual(1208736537, null, equalTo("unexpected exception: unexpected exception")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.getServiceVersion(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "unexpected exception";
            String role = "allowed";
            int status = 500;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = noEntries(String.class, String.class);
            String id = "10";
            String user = "allowed";
        }.matching(DBRequest.class));
    }

    @Test
    public void testGetServiceVersion3() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("100");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = emptyMap();
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 100")).addFreeVirtual(1208736537, null, equalTo("finished request 100")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.getServiceVersion(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "cannot find service version";
            String role = "allowed";
            int status = 422;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = noEntries(String.class, String.class);
            String id = "100";
            String user = "allowed";
        }.matching(DBRequest.class));
    }

    @Test
    public void testRequestOffer4() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("1");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = new HashMap<>();
        map1.put("offerId", "1");
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 1")).addFreeVirtual(1208736537, null, equalTo("finished request 1")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.requestOffer(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "1";
            String role = "allowed";
            int status = 200;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = containsEntries(String.class, String.class).entry("offerId", "1");
            String id = "1";
            String user = "allowed";
        }.matching(DBRequest.class));
    }

    @Test
    public void testRequestOffer5() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("2");
        dBRequest1.setUser("denied");
        Map<String, String> map1 = new HashMap<>();
        map1.put("offerId", "2");
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 2")).addFreeVirtual(1208736537, null, equalTo("finished request 2"))
            .fakeOutput(new Aspect() {
                public void warn(String string1) {
                }
            }).addFreeVirtual(1208736537, null, equalTo("user denied is not authorized")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.requestOffer(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "Access denied";
            String role = null;
            int status = 401;
            String user = null;
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = containsEntries(String.class, String.class).entry("offerId", "2");
            String id = "2";
            String user = "denied";
        }.matching(DBRequest.class));
    }

    @Test
    public void testRequestOffer6() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("3");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = new HashMap<>();
        map1.put("offerId", "broken");
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 3")).addFreeVirtual(1208736537, null, equalTo("finished request 3"))
            .fakeOutput(new Aspect() {
                public void warn(String string1) {
                }
            }).addFreeVirtual(1208736537, null, equalTo("unexpected exception: For input string: \"broken\"")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.requestOffer(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "For input string: \"broken\"";
            String role = "allowed";
            int status = 500;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = containsEntries(String.class, String.class).entry("offerId", "broken");
            String id = "3";
            String user = "allowed";
        }.matching(DBRequest.class));
    }

    @Test
    public void testRequestOffer7() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("10");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = new HashMap<>();
        map1.put("offerId", "10");
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 10")).addFreeVirtual(1208736537, null, equalTo("finished request 10"))
            .fakeOutput(new Aspect() {
                public void warn(String string1) {
                }
            }).addFreeVirtual(1208736537, null, equalTo("unexpected exception: unexpected exception")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.requestOffer(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "unexpected exception";
            String role = "allowed";
            int status = 500;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = containsEntries(String.class, String.class).entry("offerId", "10");
            String id = "10";
            String user = "allowed";
        }.matching(DBRequest.class));
    }

    @Test
    public void testRequestOffer8() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("100");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = new HashMap<>();
        map1.put("offerId", "100");
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 100")).addFreeVirtual(1208736537, null, equalTo("finished request 100")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.requestOffer(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "cannot find offer for id 100";
            String role = "allowed";
            int status = 422;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = containsEntries(String.class, String.class).entry("offerId", "100");
            String id = "100";
            String user = "allowed";
        }.matching(DBRequest.class));
    }

    @Test
    public void testAddPosition9() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("1");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = new HashMap<>();
        map1.put("price", "42.0");
        map1.put("offerId", "1");
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 1")).addFreeVirtual(1208736537, null, equalTo("finished request 1")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.addPosition(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "1:42.0";
            String role = "allowed";
            int status = 200;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = containsEntries(String.class, String.class).entry("price", "42.0").entry("offerId", "1");
            String id = "1";
            String user = "allowed";
        }.matching(DBRequest.class));
    }

    @Test
    public void testAddPosition10() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("2");
        dBRequest1.setUser("denied");
        Map<String, String> map1 = new HashMap<>();
        map1.put("price", "42.0");
        map1.put("offerId", "1");
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 2")).addFreeVirtual(1208736537, null, equalTo("finished request 2"))
            .fakeOutput(new Aspect() {
                public void warn(String string1) {
                }
            }).addFreeVirtual(1208736537, null, equalTo("user denied is not authorized")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.addPosition(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "Access denied";
            String role = null;
            int status = 401;
            String user = null;
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = containsEntries(String.class, String.class).entry("price", "42.0").entry("offerId", "1");
            String id = "2";
            String user = "denied";
        }.matching(DBRequest.class));
    }

    @Test
    public void testAddPosition11() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("3");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = new HashMap<>();
        map1.put("price", "21.0");
        map1.put("offerId", "2");
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 3")).addFreeVirtual(1208736537, null, equalTo("finished request 3")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.addPosition(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "2:21.0";
            String role = "allowed";
            int status = 200;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = containsEntries(String.class, String.class).entry("price", "21.0").entry("offerId", "2");
            String id = "3";
            String user = "allowed";
        }.matching(DBRequest.class));
    }

    @Test
    public void testAddPosition12() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("4");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = new HashMap<>();
        map1.put("price", "broken");
        map1.put("offerId", "broken");
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 4")).addFreeVirtual(1208736537, null, equalTo("finished request 4")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.addPosition(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = null;
            String role = "allowed";
            int status = 0;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = containsEntries(String.class, String.class).entry("price", "broken").entry("offerId", "broken");
            String id = "4";
            String user = "allowed";
        }.matching(DBRequest.class));
    }

    @Test
    public void testAddPosition13() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("10");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = new HashMap<>();
        map1.put("price", "11.0");
        map1.put("offerId", "10");
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 10")).addFreeVirtual(1208736537, null, equalTo("finished request 10"))
            .fakeOutput(new Aspect() {
                public void warn(String string1) {
                }
            }).addFreeVirtual(1208736537, null, equalTo("unexpected exception: unexpected exception")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.addPosition(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "unexpected exception";
            String role = "allowed";
            int status = 500;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = containsEntries(String.class, String.class).entry("price", "11.0").entry("offerId", "10");
            String id = "10";
            String user = "allowed";
        }.matching(DBRequest.class));
    }

    @Test
    public void testAddPosition14() throws Exception {

        // Arrange
        String[] stringArray1 = new String[] { "allowed" };
        List<String> list1 = asList(stringArray1);
        AuthenticationService authenticationService1 = new GenericObject() {
            List<String> authenticated = list1;
        }.as(FakeAuthenticationService.class);
        FakeSerializationService fakeSerializationService1 = new FakeSerializationService();
        FakeBusinessService fakeBusinessService1 = new FakeBusinessService();
        DBService dBService1 = new DBService(authenticationService1, fakeSerializationService1, fakeBusinessService1);
        DBRequest dBRequest1 = new DBRequest();
        dBRequest1.setId("100");
        dBRequest1.setUser("allowed");
        Map<String, String> map1 = new HashMap<>();
        map1.put("price", "11.0");
        map1.put("offerId", "100");
        dBRequest1.setAttributes(map1);
        FakeIO logger1 = FakeIO.fake(ch.qos.logback.classic.Logger.class).fakeOutput(new Aspect() {
            public void info(String string1) {
            }
        }).addFreeVirtual(1208736537, null, equalTo("starting request 100")).addFreeVirtual(1208736537, null, equalTo("finished request 100")).setup();

        // Act
        DBResponse dBResponse1 = dBService1.addPosition(dBRequest1);

        // Assert
        logger1.verify();
        assertThat(dBResponse1, new GenericMatcher() {
            Object payload = "cannot find price for id 100";
            String role = "allowed";
            int status = 422;
            String user = "allowed";
        }.matching(DBResponse.class));
        assertThat("expected no change, but was:", dBService1, new GenericMatcher() {
            Matcher<?> auth = new GenericMatcher() {
                Matcher<?> authenticated = containsInOrder(String.class, "allowed");
            }.matching(FakeAuthenticationService.class, AuthenticationService.class);
            Matcher<?> business = new GenericMatcher() {
            }.matching(FakeBusinessService.class, BusinessService.class);
            Matcher<?> serializer = new GenericMatcher() {
            }.matching(FakeSerializationService.class, SerializationService.class);
        }.matching(DBService.class));
        assertThat("expected no change, but was:", dBRequest1, new GenericMatcher() {
            Matcher<?> attributes = containsEntries(String.class, String.class).entry("price", "11.0").entry("offerId", "100");
            String id = "100";
            String user = "allowed";
        }.matching(DBRequest.class));
    }
}
