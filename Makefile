
APP_NAME=app1

SYSTEM_JARS= /usr/share/java/servlet-api.jar /usr/share/java/jsp-api.jar
JAR_SRCS=

JARS_CLASSPATH=$(shell perl -e 'print join(":",@ARGV);' $(SYSTEM_JARS) $(JAR_SRCS))

XJC= $(JVM)/bin/xjc
JVM= /usr/lib/jvm/java-6-openjdk

JAVAC=$(JVM)/bin/javac
JAVA=$(JVM)/bin/java

JARS_D=WEB-INF/lib
DATA=$(shell find . -name '*.jsp')
JAVAS=$(shell find WEB-INF/classes -name '*.java')
XSDS=$(shell find . -name '*.xsd')
CLASSES=$(JAVAS:%.java=%.class)
JARS=$(foreach jar,$(JAR_SRCS),$(JARS_D)/$(notdir $(jar)))

TEST_CLASS=tomcatExample1.auth.Parser

all: ${APP_NAME}.war

test: all
	$(JAVA) $(JFLAGS) -classpath WEB-INF/classes:$(JARS_CLASSPATH) tomcatExample1.$(TEST_CLASS)

javadoc: ${CLASSES}
	javadoc -d javadoc -classpath WEB-INF/classes tomcatExample1

jaxb: $(XSDS)
	$(XJC) -d WEB-INF/classes -p tomcatExample1.auth -readOnly WEB-INF/classes/tomcatExample1/auth/auth.xsd
	$(XJC) -d WEB-INF/classes -p tomcatExample1.config -readOnly WEB-INF/classes/tomcatExample1/config/config.xsd
	$(XJC) -d WEB-INF/classes -p tomcatExample1.opml -readOnly WEB-INF/classes/tomcatExample1/opml/opml.xsd

define COPY_JAR_template
$(JARS_D)/$(notdir $(1)): $(1)
	mkdir -p $(JARS_D) && cp -f $(1) $(JARS_D)/$(notdir $(1))
endef

$(foreach jar_src,$(JAR_SRCS),$(eval $(call COPY_JAR_template,$(jar_src))))

define REQ_CLASS_template
$(1): $(SYSTEM_JARS)
endef

$(foreach class_path,$(CLASSES),$(eval $(call REQ_CLASS_template,$(class_path))))

%.class: %.java
	$(JAVAC) -cp WEB-INF/classes:$(JARS_CLASSPATH) -target 1.5 $<

${APP_NAME}.war: ${DATA} ${CLASSES} ${JARS} $(wildcard WEB-INF/*.xml)
	rm -f $@
	zip $@ $^
	find WEB-INF/classes -name \*\$\*.class | xargs zip $@

# deps

WEB-INF/classes/tomcatExample1/Feed.class: WEB-INF/classes/tomcatExample1/Entry.class
