# YahooUIAutomation
Yahoo Reseller Purchase and Cancel
									=======================
										Framework Architecture:
										=======================

Project - Maven
Framework - Page Object Model[POM] using Page Factory in Selenium.
Language - Java
WebDriver - 3.12 version
Browsers - chrome and firefox
Test Executions - TestNG (or) maven test
Screenshots - {rootfolder}\target\surefire-reports\Screenshots\passed & {rootfolder}\target\surefire-reports\Screenshots\failed
Logger - Log4j - {rootfolder}/target/Log.log
Reports - testng reports - {rootfolder}\target\surefire-reports\index.html, {rootfolder}\target\surefire-reports\emailable-report.html
	      ReportNG reports -  {rootfolder}\target\surefire-reports\html\index.html
Editor - Eclipse

=================
Folder Structure
=================
src/main/java - Individual pages are classified in to their corresponding packages and written inside java file
				Framework related codes

src/main/resources - contains config folder gmail properties,driver folder Chromedriver exe, properties file and log4j.xml file

src/test/java - TestNG test file -com.oath.automation.yahooreseller.test/ExistingPaymentSubscriptionCancelTest/NewPaymentSubscriptionCancelTest

src/test/resources - contains TestNG.xml file in SuiteFiles/ExistingPaymentSubscriptionCancel.xml/NewPaymentSubscriptionCancel.xml.xml

target/Log.log - contains test execution log informations

Screenshots - contains test execution passed/failed screenshots if executed as TestNG tests

pom.xml - contains dependencies,plugins,etc

===========
Executions:
===========
=======
TestNG
=======
1)For NewPaymentSubscription:
 Right click on src/test/resources/SuiteFiles/NewPaymentSubscriptionCancel.xml.xml and Run As-> TestNG Suite
 
2)For ExistingPayment:
 Right click on src/test/resources/SuiteFiles/ExistingPaymentSubscriptionCancel.xml and Run As-> TestNG Suite

(or)
3) Right click on pom.xml and Run As->Maven test

Reports - testng reports - {rootfolder}\target\surefire-reports\index.html, {rootfolder}\target\surefire-reports\emailable-report.html
	      ReportNG reports -  {rootfolder}\target\surefire-reports\html\index.html
Screenshots - {rootfolder}\Screenshots\passed & {rootfolder}\Screenshots\failed
Logger - {rootfolder}/target/Log.log


## Gradle Integration commands

```
  ./gradlew clean build runTests -x test
```
