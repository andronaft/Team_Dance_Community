<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>update</name>
   <tag></tag>
   <elementGuidId>844210fa-be76-4ee0-a18a-e54aa20b310f</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;id\&quot;:\&quot;17\&quot;,\n\t\&quot;name\&quot;:\&quot;name\&quot;,\n\t\&quot;branchId\&quot;:\&quot;7\&quot;,\n    \&quot;hallId\&quot;:\&quot;1\&quot;,\n\t\&quot;capacity\&quot;:\&quot;20\&quot;,\n\t\&quot;location\&quot;:\&quot;location\&quot;,\n\t\&quot;time\&quot;:\&quot;18:00:00\&quot;,\n\t\&quot;weekDay\&quot;:\&quot;FRIDAY\&quot;,\n\t\&quot;status\&quot;:\&quot;NOT_ACTIVE\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iLCJST0xFX1RSQUlORVIiXSwiaWF0IjoxNTg4NzI1MTU5LCJleHAiOjE1ODg3NjExNTl9.SJn354WQ5MItIB3o4XmuVgPnJPKRkkDPmZ7DNBwIgIc</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://team-dance-community.herokuapp.com/api/v1/admin/training/groupTraining/update</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
