# PMD Rules for Preventing Biased Language

This repository contains a PMD RuleSet for detecting and preventing the
use of racially charged or biased language in Java codebases.

# How to use

## With Maven

Use the `maven-pmd-plugin` and include this ruleset (and its dependency
on the `com.signalfx.public:pmd-biased-language` artifact) in your
plugin configuration:

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-pmd-plugin</artifactId>
  <version>3.13.0</version>
  <executions>
    <execution>
      <id>pmd-test-compile</id>
      <phase>test-compile</phase>
      <goals>
        <goal>check</goal>
      </goals>
      <configuration>
      </configuration>
    </execution>
  </executions>
  <dependencies>
    <dependency>
      <groupId>com.signalfx.public</groupId>
      <artifactId>pmd-biased-language</artifactId>
      <version>1.0.0</version>
    </dependency>
  </dependencies>
  <configuration>
    <rulesets>
      <ruleset>pmd-biased-language/pmd.xml</ruleset>
    </rulesets>
    <excludeRoots>
      <excludeRoot>target/generated-sources</excludeRoot>
    </excludeRoots>
    <includeTests>true</includeTests>
    <linkXRef>false</linkXRef>
    <printFailingErrors>true</printFailingErrors>
  </configuration>
</plugin>
```

# Rule priority

The default priority of the rules in this ruleset is 3, like most PMD
rules. PMD also uses a default `failurePriority` of 5, meaning all rules
fail your build. If you want to use this ruleset as a warning only, you
can override the priority of its rules to 5:

```xml
<rule ref="pmd-biased-language/pmd.xml">
  <priority>5</priority>
</rule>
```

And set `<failurePriority>4</failurePriority>` in the configuration of
your `maven-pmd-plugin` execution.

# License

This PMD ruleset, its rule definitions, and the rule implementations
that it contains, are all released under the terms of the Apache
Software License 2.0.

# Links

* [Terminology, Power and Inclusive Language IETF Draft](https://tools.ietf.org/id/draft-knodel-terminology-02.html)

# Releasing

1. Deploy the snapshot:
   ```
   $ mvn -DperformRelease=true clean deploy
   ```
1. Set the version by editing the `pom.xml` file
1. Deploy the release:
   ```
   $ mvn -DperformRelease=true clean deploy -P release
   ```
1. Edit the `pom.xml` file again to go to the next snapshot version.
