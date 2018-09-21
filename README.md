# API-Adaptation

## API Changes

The following changes were taken from the article `How Does Web Service API Evolution Affect Clients?`. The main characteristic of this changes is that are clasified into the breaking category and are divided in two goups, those who are detected in `compile time` and the others in `run time`.

| Type of change                            | category |
|----------------                           | -------- |
|Decrease or Increase Number of Parameter   | compile time |
|Change Type of Parameter                   | compile time |
|Change Type of Return Value                | compile time |
|Delete Method                              | compile time |
|Rename Method                              | compile time |
|Change XML Tag                             | compile time |
|Rename Parameter                           | compile time |
|Change Format of Parameter                 | compile time |
|Change Format of Return Value              | compile time |
|Combine Methods                            | compile time |
|Split Method                               | compile time |
|Expose Data                                | compile time |
|Unsupport Request Method                   | run time |
|Change Default Value of Parameter          | run time |
|Change Upper Bound of Parameter            | run time |
|Restrict Access to API                     | run time |

## Classic repository diff

The idea of the following work is compare different versions of repositories of open source APIs that are used in the IoT context with the objective of detect match between the API changes table and the diff result. Another objective is to find repetitive patterns of change.

`git diff --stat`


### Thinks Board

ThingsBoard is an open-source IoT platform for data collection, processing, visualization, and device management.

The platform uses a Cloud distribution model, where they provisioning the software into the AWS, Google or Azure Cloud providers and bill for the service.

The platform have been developed using Java for the API endpoints and controllers. The folder used to perform the diff process will be `application/src/main/java/org/thingsboard/server/controller`, in this location the controllers are defined. 

[Thingsboard GitHub repository](https://github.com/thingsboard/thingsboard/)

-----------

#### v2.0 - v1.0

|||
|-|-|
| files changed | 26    |
| insertions    | 3437  |
| deletetions   | 832   |

`AdminController.java`  
* New method added

`AlarmController.java`
* New controller      

`AssetController.java`
* New controller      

`AuditLogController.java`
* New controller    

`AuthController`
* /auth/changePassword, parameter change
```
public void changePassword (
            @RequestParam(value = "currentPassword") String currentPassword,
            @RequestParam(value = "newPassword") String newPassword
            )
``` 

```
public void changePassword (
            @RequestBody JsonNode changePasswordRequest
            )
```

* /noauth/resetPasswordByEmail, parameter change

```
public void requestResetPasswordByEmail (
            @RequestParam(value = "email") String email,
            HttpServletRequest request
            )
```

```
public void requestResetPasswordByEmail (
            @RequestBody JsonNode resetPasswordByEmailRequest,
            HttpServletRequest request
            )
``` 

* /noauth/activate

```
public JsonNode activateUser(
            @RequestParam(value = "activateToken") String activateToken,
            @RequestParam(value = "password") String password,
            HttpServletRequest request
            )
```

```
public JsonNode activateUser(
            @RequestBody JsonNode activateRequest,
            HttpServletRequest request
            )
```


git diff --stat release-1.0:application/src/main/java/org/thingsboard/server/controller/AuditLogController.java.java master:application/src/main/java/org/thingsboard/server/controller/AdminController.java

git diff release-1.0:application/src/main/java/org/thingsboard/server/controller/AuthController.java master:application/src/main/java/org/thingsboard/server/controller/AuthController.java

### Zetta 

Zetta is an open source, API-first, Node.js-based platform for the Internet of Things. It was written using javascript and is distrubited through npm version manager, in release versions we have multiple options from 1.5.1 until 1.0.0.

[Zetta GitHub repository](https://github.com/zettajs/zetta)

-----------

#### v1.5.1 - v1.5.0

**v1.5.1 commit:** aa5b00768636b5272b3727c6d33eff25dc914ed0

**v1.5.0 commit:** 5a1bc56aad75fb63ab3648c700bc8f2d6041c889

|||
|-|-|
| files changed | 3     |
| insertions    | 47    |
| deletetions   | 5     |

This alteration did not includes any API change.

-----------

#### v1.5.0 - v1.4.0

**v1.5.0 commit:** 5a1bc56aad75fb63ab3648c700bc8f2d6041c889

**v1.4.0 commit:** 0129dc74ea88d74c71606893c24be3f7332dd453

|||
|-|-|
| files changed | 6     |
| insertions    | 34    |
| deletetions   | 32    |

In this alteration were a lot of changes in the Web Socket feature, but nothing representative in the API.

-----------

#### v1.5.0 - v1.0.0

**v1.5.0 commit:** 5a1bc56aad75fb63ab3648c700bc8f2d6041c889

**v1.0.0 commit:** 0a1c176cc697df9b555b1aecd51d31cf8d44274f

|||
|-|-|
| files changed | 17     |
| insertions    | 944    |
| deletetions   | 189    |

The files that define API endpoints are `lib/api_resources/servers.js` and `lib/api_resources/peer_management.js`. These files remained with almost no changes between the versions 1.0.0 and 1.5.0, the endpoints or methods signature did were not modified during this time. That means that `ZettaJS` is not a good open source API to work with.

# API-Metamodel 

We are using a metamodel proposed by the open source project [APIDiscoverer](https://github.com/SOM-Research/APIDiscoverer) 

```
APIDiscoverer/metamodel/
```
It's the project thats desribes the REST Web API metamodel. 