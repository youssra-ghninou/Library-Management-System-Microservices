# Swagger not supported anymore

## Solution found at : https://stackoverflow.com/questions/40241843/failed-to-start-bean-documentationpluginsbootstrapper-in-spring-data-rest

## New solution : https://springdoc.org/#migrating-from-springfox

## Doc available at : http://localhost:1001/swagger-ui/index.html#/

This issue is caused by Spring Fox 3.0.0 not supporting new PathPattern Based Path Matching Strategy for Spring MVC which is now the new default from spring-boot 2.6.0.

There are actually some workarounds for this issue like:

Downgrading spring-boot back to version lower than 2.6.0. I wouldn't recommend that due to major security issues with older versions of spring boot / spring framework (like log4shell or CVE-2022-22965 for example.)
Changing PathPattern matching back to AntPatternMatching in yml, like mentioned by @santhanam.
SpringFox hasn't been updated for a year or so, so I would prefer remove it completely from a project and replace it with maintained springdoc-openapi library. There is a section in springdoc documentation about migration from SpringFox and it's actually very straightforward to perform. And you'll get rid of issues caused by SpringFox completely.