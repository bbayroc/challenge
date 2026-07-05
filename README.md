# Notification SDK

A lightweight, framework-agnostic Java library that provides a unified API for sending notifications through multiple channels.

The library focuses on **architecture**, **extensibility**, and **clean design**, allowing applications to switch providers or add new notification channels without changing business logic.

---

## Features

- Unified notification API
- Email notifications
- SMS notifications
- Push notifications
- Multiple providers per channel
    - SendGrid
    - Mailgun
    - Twilio
    - Firebase
- Java-based configuration
- Retry policies
- Circuit Breaker
- Validation
- Message templates
- Batch notifications
- Asynchronous notifications
- EventBus (Pub/Sub)
- Framework agnostic
- Java 21
- Maven project

---

# Architecture
                     +----------------------+
                     | NotificationSDK      |
                     +----------+-----------+
                                |
                                v
                    +------------------------+
                    | NotificationManager    |
                    +-----------+------------+
                                |
                                v
                    +------------------------+
                    | ExecutionPipeline      |
                    +-----------+------------+
                                |
          +---------------------+----------------------+
          |                     |                      |
          v                     v                      v
ValidationExecutor      RetryExecutor        CircuitBreaker
|                     |                      |
+---------------------+----------------------+
|
v
+--------------------+
| ChannelResolver    |
+---------+----------+
|
+-------------------------+----------------------------+
|                         |                            |
v                         v                            v
EmailChannelHandler    SmsChannelHandler        PushChannelHandler
|                         |                            |
v                         v                            v
EmailSender             SmsSender               PushProvider
|                         |
v                         v
EmailProvider           SmsProvider
|                         |
+-----------+-------------+
|
Different Providers
---

# Design Goals

The project was designed around the following principles:

- Framework agnostic
- SOLID principles
- Extensibility
- Separation of responsibilities
- Composition over inheritance
- Testability

Every major component has a single responsibility, making the library easy to extend and maintain.

---

# Supported Channels

| Channel | Status |
|----------|--------|
| Email | ✅ |
| SMS | ✅ |
| Push Notification | ✅ |
| Slack | Planned |

---

# Supported Providers

## Email

- SendGrid
- Mailgun

## SMS

- Twilio

## Push

- Firebase Cloud Messaging (simulation)

---

# Project Structure

```
src
 ├── config
 ├── core
 ├── event
 ├── examples
 ├── exception
 ├── factory
 ├── model
 ├── provider
 ├── sender
 ├── template
 ├── validation
 └── tests
```

---

# Why this library?

Most notification libraries are tightly coupled to a single provider.

This project separates:

- Notification channels
- Providers
- Validation
- Retry policies
- Execution pipeline
- Events

allowing applications to replace providers or add new channels without changing client code.
# Requirements

- Java 21 or later
- Maven 3.9+
- No external frameworks required
- SLF4J compatible logger (optional)

The library is completely framework agnostic and can be used in:

- Plain Java applications
- Spring Boot
- Quarkus
- Micronaut
- Jakarta EE
- Desktop applications
- CLI tools

No annotations, dependency injection containers, or external configuration files are required.

---

# Installation

## Maven

If the library is published to Maven Central:

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>notifications-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

If using the project locally:

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>notifications-sdk</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

---

## Gradle

```gradle
implementation "com.example:notifications-sdk:1.0.0"
```

---

# Building from Source

Clone the repository:

```bash
git clone https://github.com/your-user/notifications-sdk.git

cd notifications-sdk
```

Compile the project:

```bash
mvn clean compile
```

Run all tests:

```bash
mvn clean test
```

Generate the package:

```bash
mvn clean package
```

---

# Project Modules

Although distributed as a single library, the internal architecture is organized into logical modules.

| Module | Responsibility |
|---------|----------------|
| config | Configuration objects |
| core | Notification pipeline |
| provider | Notification providers |
| sender | Channel implementations |
| validation | Notification validation |
| model | Domain models |
| event | Pub/Sub event system |
| template | Template engine |
| factory | Object creation |
| examples | Usage examples |

---

# Configuration Philosophy

Unlike many notification libraries, this project intentionally avoids:

- YAML
- properties files
- XML
- dependency injection containers
- framework-specific configuration

Everything is configured through Java objects.

Example:

```java
EmailConfiguration configuration =
        EmailConfiguration.builder()
                .provider(
                        new SendGridProvider(
                                SendGridConfiguration.builder()
                                        .apiKey("your-api-key")
                                        .build()
                        )
                )
                .defaultFrom("noreply@example.com")
                .build();
```

This approach provides:

- compile-time safety
- IDE autocompletion
- immutable configuration objects
- framework independence

---

# Library Overview

The library separates responsibilities into independent layers.

```

```text
Application
      │
      ▼
NotificationSDK
      │
      ▼
NotificationManager
      │
      ▼
Execution Pipeline
      │
      ▼
Channel Resolver
      │
      ▼
Channel Handler
      │
      ▼
Sender
      │
      ▼
Provider
```
# Requirements

- Java 21 or later
- Maven 3.9+
- No external frameworks required
- SLF4J compatible logger (optional)

The library is completely framework agnostic and can be used in:

- Plain Java applications
- Spring Boot
- Quarkus
- Micronaut
- Jakarta EE
- Desktop applications
- CLI tools

No annotations, dependency injection containers, or external configuration files are required.

---

# Installation

## Maven

If the library is published to Maven Central:

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>notifications-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

If using the project locally:

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>notifications-sdk</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

---

## Gradle

```gradle
implementation "com.example:notifications-sdk:1.0.0"
```

---

# Building from Source

Clone the repository:

```bash
git clone https://github.com/your-user/notifications-sdk.git

cd notifications-sdk
```

Compile the project:

```bash
mvn clean compile
```

Run all tests:

```bash
mvn clean test
```

Generate the package:

```bash
mvn clean package
```

---

# Project Modules

Although distributed as a single library, the internal architecture is organized into logical modules.

| Module | Responsibility |
|---------|----------------|
| config | Configuration objects |
| core | Notification pipeline |
| provider | Notification providers |
| sender | Channel implementations |
| validation | Notification validation |
| model | Domain models |
| event | Pub/Sub event system |
| template | Template engine |
| factory | Object creation |
| examples | Usage examples |

---

# Configuration Philosophy

Unlike many notification libraries, this project intentionally avoids:

- YAML
- properties files
- XML
- dependency injection containers
- framework-specific configuration

Everything is configured through Java objects.

Example:

```java
EmailConfiguration configuration =
        EmailConfiguration.builder()
                .provider(
                        new SendGridProvider(
                                SendGridConfiguration.builder()
                                        .apiKey("your-api-key")
                                        .build()
                        )
                )
                .defaultFrom("noreply@example.com")
                .build();
```

This approach provides:

- compile-time safety
- IDE autocompletion
- immutable configuration objects
- framework independence

---

# Library Overview

The library separates responsibilities into independent layers.

```

```text
Application
      │
      ▼
NotificationSDK
      │
      ▼
NotificationManager
      │
      ▼
Execution Pipeline
      │
      ▼
Channel Resolver
      │
      ▼
Channel Handler
      │
      ▼
Sender
      │
      ▼
Provider
```
# Quick Start

The following example demonstrates the minimum configuration required to send a notification using the library.

## 1. Configure an Email Provider

```java
EmailConfiguration emailConfiguration =
        EmailConfiguration.builder()
                .provider(
                        new SendGridProvider(
                                SendGridConfiguration.builder()
                                        .apiKey("your-api-key")
                                        .build()
                        )
                )
                .defaultFrom("notifications@example.com")
                .build();
```

---

## 2. Configure an SMS Provider

```java
SmsConfiguration smsConfiguration =
        SmsConfiguration.builder()
                .provider(
                        new TwilioProvider(
                                new TwilioConfiguration()
                        )
                )
                .build();
```

---

## 3. Configure Retry Policy

```java
RetryPolicy retryPolicy =
        new ExponentialBackoffRetryPolicy(3);
```

The previous configuration retries failed deliveries up to **three times** using exponential backoff.

---

## 4. Configure Circuit Breaker

```java
CircuitBreaker circuitBreaker =
        new CircuitBreaker(
                3,
                5000
        );
```

Meaning:

- Open the circuit after **3 failed operations**
- Wait **5 seconds** before allowing another request

---

## 5. Create the EventBus

```java
EventBus eventBus = new EventBus();
```

The EventBus is optional but recommended.

It allows applications to react to notification events without modifying the notification pipeline.

---

## 6. Create the NotificationManager

```java
NotificationManager manager =
        NotificationFactory.createManager(
                emailConfiguration,
                smsConfiguration,
                retryPolicy,
                circuitBreaker,
                eventBus
        );
```

The factory assembles the complete execution pipeline.

Applications never need to instantiate internal components directly.

---

# Sending Notifications

## Email

```java
EmailNotification email =
        EmailNotification.builder()
                .recipient("john@example.com")
                .subject("Welcome")
                .message("Welcome to Notification SDK!")
                .build();

NotificationResult result =
        manager.send(email);
```

---

## SMS

```java
SmsNotification sms =
        SmsNotification.builder()
                .phoneNumber("+51999999999")
                .message("Verification code: 123456")
                .build();

NotificationResult result =
        manager.send(sms);
```

---

## Push Notification

```java
PushNotification push =
        PushNotification.builder()
                .deviceToken("device-token")
                .title("New Message")
                .message("You have a new notification")
                .build();

NotificationResult result =
        manager.send(push);
```

---

# Reading the Result

Every notification returns a `NotificationResult`.

```java
NotificationResult result =
        manager.send(email);

System.out.println(result.getStatus());
System.out.println(result.getProvider());
System.out.println(result.getMessageId());
System.out.println(result.getStatusCode());
System.out.println(result.getDuration());
```

Typical output:

```
Status      : SUCCESS
Provider    : SendGrid
Status Code : 200
Message ID  : 8dc2e0...
Duration    : PT0.081S
```

---

# Error Handling

Validation errors and delivery failures are handled separately.

Validation example:

```java
try {

    manager.send(email);

} catch (ValidationException ex) {

    System.out.println(ex.getMessage());

}
```

Delivery example:

```java
NotificationResult result =
        manager.send(email);

if (result.getStatus() == NotificationStatus.FAILED) {

    System.out.println(result.getErrorMessage());

}
```

This separation allows applications to distinguish between invalid input and provider failures.

---

# Using the SDK Facade

Applications may also use the higher-level `NotificationSDK` facade.

```java
NotificationSDK sdk =
        new NotificationSDK.Builder()
                .email(emailConfiguration)
                .sms(smsConfiguration)
                .retryPolicy(retryPolicy)
                .circuitBreaker(circuitBreaker)
                .eventBus(eventBus)
                .build();

sdk.send(email);
```

For most applications this is the recommended entry point.

---

# Typical Application Flow

```
Application
      │
      ▼
NotificationSDK
      │
      ▼
NotificationManager
      │
      ▼
ExecutionPipeline
      │
      ▼
Validation
      │
      ▼
Retry
      │
      ▼
Circuit Breaker
      │
      ▼
Channel Resolver
      │
      ▼
Provider
      │
      ▼
NotificationResult
```

---

# Supported Notification Types

| Notification | Builder | Required Fields |
|--------------|---------|-----------------|
| EmailNotification | ✅ | recipient, subject, message |
| SmsNotification | ✅ | phoneNumber, message |
| PushNotification | ✅ | deviceToken, title, message |

All notification models are immutable and created using the Builder pattern.
# Configuration Guide

All configuration in Notification SDK is performed using immutable Java objects.

The library intentionally avoids external configuration files such as:

- application.yml
- application.properties
- XML configuration
- Dependency Injection containers

This makes the library portable across any Java application.

---

# Email Configuration

The Email channel requires an `EmailConfiguration` object.

```java
EmailConfiguration configuration =
        EmailConfiguration.builder()
                .provider(
                        new SendGridProvider(
                                SendGridConfiguration.builder()
                                        .apiKey("YOUR_API_KEY")
                                        .timeout(Duration.ofSeconds(30))
                                        .build()
                        )
                )
                .defaultFrom("notifications@example.com")
                .build();
```

Available options:

| Property | Description |
|----------|-------------|
| provider | Email provider implementation |
| defaultFrom | Default sender address |
| timeout | Provider timeout |

---

# SendGrid Provider

```java
SendGridConfiguration configuration =
        SendGridConfiguration.builder()
                .apiKey("YOUR_API_KEY")
                .timeout(Duration.ofSeconds(30))
                .build();

EmailProvider provider =
        new SendGridProvider(configuration);
```

Current simulated response:

```
HTTP Status : 200
Provider    : SendGrid
Result      : SUCCESS
```

---

# Mailgun Provider

```java
MailgunConfiguration configuration =
        MailgunConfiguration.builder()
                .apiKey("YOUR_API_KEY")
                .timeout(Duration.ofSeconds(30))
                .build();

EmailProvider provider =
        new MailgunProvider(configuration);
```

Current simulated response:

```
HTTP Status : 200
Provider    : Mailgun
Result      : SUCCESS
```

---

# SMS Configuration

```java
SmsConfiguration configuration =
        SmsConfiguration.builder()
                .provider(
                        new TwilioProvider(
                                new TwilioConfiguration()
                        )
                )
                .timeout(Duration.ofSeconds(30))
                .build();
```

Available options:

| Property | Description |
|----------|-------------|
| provider | SMS provider |
| timeout | Delivery timeout |

---

# Twilio Provider

```java
TwilioProvider provider =
        new TwilioProvider(
                new TwilioConfiguration()
        );
```

Simulated response:

```
HTTP Status : 200
Provider    : Twilio
Result      : SUCCESS
```

---

# Push Notifications

Push notifications do not currently require additional configuration.

The default provider is Firebase.

```java
PushProvider provider =
        new FirebasePushProvider();
```

Simulated response:

```
HTTP Status : 200
Provider    : Firebase
Result      : SUCCESS
```

---

# Retry Policies

Notification SDK includes interchangeable retry strategies.

## Fixed Retry

Retries using a constant delay.

```java
RetryPolicy retryPolicy =
        new FixedRetryPolicy(
                3,
                1000
        );
```

Meaning:

- maximum attempts: 3
- delay between attempts: 1 second

---

## Exponential Backoff

Delay increases after every retry.

```java
RetryPolicy retryPolicy =
        new ExponentialBackoffRetryPolicy(3);
```

Typical delays:

| Attempt | Delay |
|---------|-------|
| 1 | 500 ms |
| 2 | 1000 ms |
| 3 | 2000 ms |

---

# Circuit Breaker

The Circuit Breaker prevents repeated calls to failing providers.

```java
CircuitBreaker breaker =
        new CircuitBreaker(
                3,
                5000
        );
```

Configuration:

| Parameter | Description |
|-----------|-------------|
| failureThreshold | Failed operations before opening |
| resetTimeoutMillis | Time before entering HALF_OPEN |

Lifecycle:

```
CLOSED
   │
   ▼
Failures
   │
   ▼
OPEN
   │
(wait timeout)
   │
   ▼
HALF_OPEN
   │
   ├── Success → CLOSED
   └── Failure → OPEN
```

---

# EventBus

Applications may subscribe to notification events.

```java
EventBus eventBus = new EventBus();

eventBus.register(
        new LoggingEventListener());

eventBus.register(
        new MetricsEventListener());
```

Current events:

| Event | Description |
|-------|-------------|
| SENT | Notification delivered |
| FAILED | Notification failed |
| CIRCUIT_OPENED | Circuit entered OPEN state |
| CIRCUIT_CLOSED | Circuit returned to CLOSED |

---

# Logging

Notification SDK uses SLF4J.

Typical provider log:

```
INFO
Simulating SendGrid email delivery to john@example.com
```

Applications are free to plug any SLF4J implementation:

- Logback
- Log4J2
- JUL bridge
- SimpleLogger

---

# Configuration Best Practices

✔ Build configuration once.

✔ Reuse provider instances.

✔ Keep API keys outside source code.

✔ Prefer immutable configuration.

✔ Share a single EventBus across the application.

✔ Reuse NotificationSDK instead of recreating it.

---

# Configuration Object Relationships

```
Application
      │
      ▼
NotificationSDK.Builder
      │
      ├───────────────┐
      ▼               ▼
EmailConfiguration   SmsConfiguration
      │               │
      ▼               ▼
EmailProvider      SmsProvider
      │               │
      ▼               ▼
SendGrid        Twilio
Mailgun
```
# Configuration Guide

All configuration in Notification SDK is performed using immutable Java objects.

The library intentionally avoids external configuration files such as:

- application.yml
- application.properties
- XML configuration
- Dependency Injection containers

This makes the library portable across any Java application.

---

# Email Configuration

The Email channel requires an `EmailConfiguration` object.

```java
EmailConfiguration configuration =
        EmailConfiguration.builder()
                .provider(
                        new SendGridProvider(
                                SendGridConfiguration.builder()
                                        .apiKey("YOUR_API_KEY")
                                        .timeout(Duration.ofSeconds(30))
                                        .build()
                        )
                )
                .defaultFrom("notifications@example.com")
                .build();
```

Available options:

| Property | Description |
|----------|-------------|
| provider | Email provider implementation |
| defaultFrom | Default sender address |
| timeout | Provider timeout |

---

# SendGrid Provider

```java
SendGridConfiguration configuration =
        SendGridConfiguration.builder()
                .apiKey("YOUR_API_KEY")
                .timeout(Duration.ofSeconds(30))
                .build();

EmailProvider provider =
        new SendGridProvider(configuration);
```

Current simulated response:

```
HTTP Status : 200
Provider    : SendGrid
Result      : SUCCESS
```

---

# Mailgun Provider

```java
MailgunConfiguration configuration =
        MailgunConfiguration.builder()
                .apiKey("YOUR_API_KEY")
                .timeout(Duration.ofSeconds(30))
                .build();

EmailProvider provider =
        new MailgunProvider(configuration);
```

Current simulated response:

```
HTTP Status : 200
Provider    : Mailgun
Result      : SUCCESS
```

---

# SMS Configuration

```java
SmsConfiguration configuration =
        SmsConfiguration.builder()
                .provider(
                        new TwilioProvider(
                                new TwilioConfiguration()
                        )
                )
                .timeout(Duration.ofSeconds(30))
                .build();
```

Available options:

| Property | Description |
|----------|-------------|
| provider | SMS provider |
| timeout | Delivery timeout |

---

# Twilio Provider

```java
TwilioProvider provider =
        new TwilioProvider(
                new TwilioConfiguration()
        );
```

Simulated response:

```
HTTP Status : 200
Provider    : Twilio
Result      : SUCCESS
```

---

# Push Notifications

Push notifications do not currently require additional configuration.

The default provider is Firebase.

```java
PushProvider provider =
        new FirebasePushProvider();
```

Simulated response:

```
HTTP Status : 200
Provider    : Firebase
Result      : SUCCESS
```

---

# Retry Policies

Notification SDK includes interchangeable retry strategies.

## Fixed Retry

Retries using a constant delay.

```java
RetryPolicy retryPolicy =
        new FixedRetryPolicy(
                3,
                1000
        );
```

Meaning:

- maximum attempts: 3
- delay between attempts: 1 second

---

## Exponential Backoff

Delay increases after every retry.

```java
RetryPolicy retryPolicy =
        new ExponentialBackoffRetryPolicy(3);
```

Typical delays:

| Attempt | Delay |
|---------|-------|
| 1 | 500 ms |
| 2 | 1000 ms |
| 3 | 2000 ms |

---

# Circuit Breaker

The Circuit Breaker prevents repeated calls to failing providers.

```java
CircuitBreaker breaker =
        new CircuitBreaker(
                3,
                5000
        );
```

Configuration:

| Parameter | Description |
|-----------|-------------|
| failureThreshold | Failed operations before opening |
| resetTimeoutMillis | Time before entering HALF_OPEN |

Lifecycle:

```
CLOSED
   │
   ▼
Failures
   │
   ▼
OPEN
   │
(wait timeout)
   │
   ▼
HALF_OPEN
   │
   ├── Success → CLOSED
   └── Failure → OPEN
```

---

# EventBus

Applications may subscribe to notification events.

```java
EventBus eventBus = new EventBus();

eventBus.register(
        new LoggingEventListener());

eventBus.register(
        new MetricsEventListener());
```

Current events:

| Event | Description |
|-------|-------------|
| SENT | Notification delivered |
| FAILED | Notification failed |
| CIRCUIT_OPENED | Circuit entered OPEN state |
| CIRCUIT_CLOSED | Circuit returned to CLOSED |

---

# Logging

Notification SDK uses SLF4J.

Typical provider log:

```
INFO
Simulating SendGrid email delivery to john@example.com
```

Applications are free to plug any SLF4J implementation:

- Logback
- Log4J2
- JUL bridge
- SimpleLogger

---

# Configuration Best Practices

✔ Build configuration once.

✔ Reuse provider instances.

✔ Keep API keys outside source code.

✔ Prefer immutable configuration.

✔ Share a single EventBus across the application.

✔ Reuse NotificationSDK instead of recreating it.

---

# Configuration Object Relationships

```
Application
      │
      ▼
NotificationSDK.Builder
      │
      ├───────────────┐
      ▼               ▼
EmailConfiguration   SmsConfiguration
      │               │
      ▼               ▼
EmailProvider      SmsProvider
      │               │
      ▼               ▼
SendGrid        Twilio
Mailgun
```
# Extending the Library

One of the main design goals of Notification SDK is extensibility.

Applications should be able to introduce new providers or even entirely new notification channels without modifying the existing execution pipeline.

The core architecture follows the **Open/Closed Principle (OCP)**.

> Open for extension.
>
> Closed for modification.

---

# Extension Points

The library exposes several extension points.

| Extension | Required Changes | Existing Core Modified |
|-----------|------------------|------------------------|
| New Email Provider | Provider implementation | ❌ No |
| New SMS Provider | Provider implementation | ❌ No |
| New Push Provider | Provider implementation | ❌ No |
| New Notification Channel | New classes + registration | ❌ No changes to execution pipeline |
| New Retry Policy | Implement RetryPolicy | ❌ No |
| New Validator | Implement Validator | ❌ No |
| New Event Listener | Implement NotificationEventListener | ❌ No |

---

# Adding a New Email Provider

Suppose we want to support Amazon SES.

The existing pipeline remains untouched.

Only a new provider implementation is required.

```
AmazonSesProvider
        │
        ▼
implements EmailProvider
```

```java
public final class AmazonSesProvider
        implements EmailProvider {

    @Override
    public NotificationResult send(
            EmailNotification notification) {

        // Simulated implementation

    }

    @Override
    public String getProviderName() {

        return "Amazon SES";

    }

}
```

No other class requires modification.

Applications simply change the configuration.

```java
EmailConfiguration configuration =
        EmailConfiguration.builder()
                .provider(
                        new AmazonSesProvider(...)
                )
                .build();
```

---

# Adding a New SMS Provider

Supporting a different SMS gateway follows exactly the same approach.

```
VonageProvider

implements SmsProvider
```

```java
SmsConfiguration configuration =
        SmsConfiguration.builder()
                .provider(
                        new VonageProvider(...)
                )
                .build();
```

No business logic changes.

No pipeline changes.

No client code changes.

---

# Adding a New Push Provider

Push notifications are also provider independent.

```
OneSignalProvider

implements PushProvider
```

Configuration changes only.

---

# Adding a New Retry Strategy

Retry behavior is completely decoupled from notification delivery.

Applications may provide any retry algorithm.

```java
public final class LinearRetryPolicy
        implements RetryPolicy {

    @Override
    public boolean shouldRetry(
            int attempt,
            Exception exception) {

        return attempt < 5;

    }

    @Override
    public long getDelayMillis(
            int attempt) {

        return 1000L;

    }

}
```

Use it:

```java
RetryPolicy retryPolicy =
        new LinearRetryPolicy();
```

No other component changes.

---

# Adding a New Event Listener

The EventBus follows the Publish/Subscribe pattern.

Applications simply implement:

```java
NotificationEventListener
```

Example:

```java
public final class AuditListener
        implements NotificationEventListener {

    @Override
    public void onEvent(
            NotificationEvent event) {

        System.out.println(
                event.getType());

    }

}
```

Registration:

```java
eventBus.register(
        new AuditListener());
```

No changes to EventBus.

---

# Adding a New Validator

Validation is isolated from notification delivery.

Example:

```java
public final class CustomEmailValidator
        implements Validator<EmailNotification> {

    @Override
    public void validate(
            EmailNotification notification) {

        // custom validation

    }

}
```

The sender can then use the new validator without affecting the rest of the system.

---

# Adding a New Notification Channel

The architecture also supports adding completely new notification channels.

For example:

```
Slack
Discord
Microsoft Teams
WhatsApp
Telegram
```

A new channel requires the following components.

```
SlackNotification

SlackProvider

SlackConfiguration

SlackValidator

SlackSender

SlackChannelHandler
```

After creating these classes, the new handler is registered inside the factory.

```
NotificationFactory

↓

handlers.add(
    new SlackChannelHandler(...)
)
```

The following classes remain unchanged:

- NotificationManager

- ExecutionPipeline

- RetryExecutor

- ValidationExecutor

- ChannelResolver

- EventBus

This demonstrates compliance with the Open/Closed Principle.

---

# Why Channel Handlers?

Instead of using:

```
if (notification instanceof EmailNotification)

if (notification instanceof SmsNotification)

if (notification instanceof PushNotification)
```

the library delegates routing to specialized handlers.

```
Notification

↓

ChannelResolver

↓

ChannelHandler

↓

Sender

↓

Provider
```

Advantages:

- No instanceof chains

- Better separation of concerns

- Easier testing

- Easier extension

---

# Dependency Inversion

High-level components never depend on concrete providers.

```
NotificationManager

↓

ChannelHandler

↓

Sender

↓

EmailProvider
```

Instead of

```
NotificationManager

↓

SendGridProvider
```

Applications choose providers through configuration.

This allows replacing providers without recompiling business logic.

---

# Composition Over Inheritance

Most components collaborate through composition.

```
NotificationManager

contains

ExecutionPipeline

contains

RetryExecutor

contains

RetryPolicy
```

This keeps responsibilities isolated and improves testability.

---

# Framework Independence

The library contains no dependency on:

- Spring Framework
- Spring Boot
- Quarkus
- Micronaut
- CDI
- Jakarta Injection

Applications remain free to integrate the SDK using any dependency injection mechanism.

---

# Architectural Benefits

The resulting architecture provides:

- Provider independence
- Framework independence
- Strong separation of concerns
- Easy testing
- Reusable components
- High cohesion
- Low coupling
- SOLID compliance
- Easy maintenance
- Future extensibility

---

# Extension Summary

| Customization | Supported |
|--------------|-----------|
| New Email Provider | ✅ |
| New SMS Provider | ✅ |
| New Push Provider | ✅ |
| New Retry Policy | ✅ |
| New Validator | ✅ |
| New Event Listener | ✅ |
| New Notification Channel | ✅ |
| Framework Integration | ✅ |
