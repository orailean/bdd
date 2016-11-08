Scenario: Find the current product version
Given a url <dmcWebDomain>
When I request the version deployed
Then the response should not be null
Examples:
| dmcWebDomain			|
| http://smoke33212.com	|
| http://smoke16112.com	|