package org.bdd.jbehave;

import java.io.File;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class SoapGetDmcVersion {
	private String dmcWebDomain;
	private String version;
	private File xmlFileTemplate;
	private String response;

	@Given("a url <dmcWebDomain>")
	public void givenAUrldmcWebDomain(String dmcWebDomain) {
		this.dmcWebDomain = dmcWebDomain;
	}

	@When("I request the version deployed")
	public void whenIRequestTheVersionDeployed() {
        xmlFileTemplate = new File("./src/test/resources/org/bdd/xml/SoapGetDmcVersion.xml");
        String xmlFileAsString = Utilities.fileToString(xmlFileTemplate);
        String dmcApiUrl = dmcWebDomain + LocalProperties.getApiEndPoint();

        SoapClient soapClient = new SoapClient(dmcApiUrl, LocalProperties.getProxyHost(),
                        LocalProperties.getProxyPort());
        response = soapClient.execute(xmlFileAsString, LocalProperties.getApiUser(), LocalProperties.getApiPassword());

        XMLParseUtils xmlUtils = new XMLParseUtils();
        version = xmlUtils.getTextFromNode(response, "version");

	}

	@Then("the response should not be null")
	public void thenTheResponseShouldNotBeNull() {
		Assert.assertNotNull(version);
	}
}
