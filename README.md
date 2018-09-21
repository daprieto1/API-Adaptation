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

```
    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/updates", method = RequestMethod.GET)
    @ResponseBody
    public UpdateMessage checkUpdates() throws ThingsboardException {
        try {
            return updateService.checkUpdates();
        } catch (Exception e) {
            throw handleException(e);
        }
    }
```

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

* /noauth/activate, parameter change

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

* /noauth/resetPassword, parameter change

```
public JsonNode resetPassword(
            @RequestParam(value = "resetToken") String resetToken,
            @RequestParam(value = "password") String password,
            HttpServletRequest request
            )
```

```
public void requestResetPasswordByEmail (
            @RequestBody JsonNode resetPasswordByEmailRequest,
            HttpServletRequest request
            )
```

`BaseController`

It does not have endpoints, all the methods extend from it.

`ComponentDescriptorController`

* /components/actions/{pluginClazz:.+}, delete method

```
    @RequestMapping(value = "/components/actions/{pluginClazz:.+}", method = RequestMethod.GET)
```

* /components, params = {"componentTypes"}, new method, overload method

```
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','TENANT_ADMIN')")
    @RequestMapping(value = "/components", params = {"componentTypes"}, method = RequestMethod.GET)
    @ResponseBody
    public List<ComponentDescriptor> getComponentDescriptorsByTypes(@RequestParam("componentTypes") String[] strComponentTypes)
```

`CustomerController`

* /customer/{customerId}/shortInfo, new method

```
    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/customer/{customerId}/shortInfo", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode getShortCustomerInfoById(@PathVariable(CUSTOMER_ID) String strCustomerId)
```

* customer/{customerId}/title, new method

```
    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/customer/{customerId}/title", method = RequestMethod.GET, produces = "application/text")
    @ResponseBody
    public String getCustomerTitleById(@PathVariable(CUSTOMER_ID) String strCustomerId)
```

* /tenant/customers, new method

```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/tenant/customers", params = {"customerTitle"}, method = RequestMethod.GET)
    @ResponseBody
    public Customer getTenantCustomer(@RequestParam String customerTitle) 
```

`DashboardController`

* /dashboard/serverTime, new method

```
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/dashboard/serverTime", method = RequestMethod.GET)
    @ResponseBody
    public long getServerTime()
```

* /dashboard/info/{dashboardId}, new method

```
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/dashboard/info/{dashboardId}", method = RequestMethod.GET)
    @ResponseBody
    public DashboardInfo getDashboardInfoById(@PathVariable(DASHBOARD_ID) String strDashboardId)
```

* /customer/dashboard/{dashboardId} => /customer/{customerId}/dashboard/{dashboardId}, delete method, add parameter, change endpoint, new method

```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/customer/dashboard/{dashboardId}", method = RequestMethod.DELETE)
    @ResponseBody 
    public Dashboard unassignDashboardFromCustomer(@PathVariable("dashboardId") String strDashboardId)
```

```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/customer/{customerId}/dashboard/{dashboardId}", method = RequestMethod.DELETE)
    @ResponseBody 
    public Dashboard unassignDashboardFromCustomer(@PathVariable("customerId") String strCustomerId,
                                                   @PathVariable(DASHBOARD_ID) String strDashboardId)
```

* /dashboard/{dashboardId}/customers, new method

```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/dashboard/{dashboardId}/customers", method = RequestMethod.POST)
    @ResponseBody
    public Dashboard updateDashboardCustomers(@PathVariable(DASHBOARD_ID) String strDashboardId,
                                              @RequestBody String[] strCustomerIds)
```

* /dashboard/{dashboardId}/customers/add, new method

```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/dashboard/{dashboardId}/customers/add", method = RequestMethod.POST)
    @ResponseBody
    public Dashboard addDashboardCustomers(@PathVariable(DASHBOARD_ID) String strDashboardId,
                                           @RequestBody String[] strCustomerIds)
```

* /dashboard/{dashboardId}/customers/remove, new method


```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/dashboard/{dashboardId}/customers/remove", method = RequestMethod.POST)
    @ResponseBody
    public Dashboard removeDashboardCustomers(@PathVariable(DASHBOARD_ID) String strDashboardId,
                                              @RequestBody String[] strCustomerIds)
```

* /customer/public/dashboard/{dashboardId}, new method

```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/customer/public/dashboard/{dashboardId}", method = RequestMethod.POST)
    @ResponseBody
    public Dashboard assignDashboardToPublicCustomer(@PathVariable(DASHBOARD_ID) String strDashboardId)
```

* /customer/public/dashboard/{dashboardId}, new method

```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/customer/public/dashboard/{dashboardId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Dashboard unassignDashboardFromPublicCustomer(@PathVariable(DASHBOARD_ID)
```

* /tenant/{tenantId}/dashboards, new method

```
    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/tenant/{tenantId}/dashboards", params = { "limit" }, method = RequestMethod.GET)
    @ResponseBody
    public TextPageData<DashboardInfo> getTenantDashboards(
            @PathVariable("tenantId") String strTenantId,
            @RequestParam int limit,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String idOffset,
            @RequestParam(required = false) String textOffset)
```

* /tenant/dashboards, change return type

```
public TextPageData<Dashboard> getTenantDashboards
```

```
public TextPageData<DashboardInfo> getTenantDashboards
```

* /customer/{customerId}/dashboards, change return type

```
public TextPageData<Dashboard> getCustomerDashboards
```

```
public TextPageData<DashboardInfo> getCustomerDashboards
```

`DeviceController`

* /device, Authorization change

```
@PreAuthorize("hasAuthority('TENANT_ADMIN')")
```

```
@PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
```

* /customer/public/device/{deviceId}, new method

```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/customer/public/device/{deviceId}", method = RequestMethod.POST)
    @ResponseBody
    public Device assignDeviceToPublicCustomer(@PathVariable(DEVICE_ID) String strDeviceId)
```

* /tenant/devices, parameter added

```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/tenant/devices", params = {"limit"}, method = RequestMethod.GET)
    @ResponseBody
    public TextPageData<Device> getTenantDevices(
            @RequestParam int limit,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String idOffset,
            @RequestParam(required = false) String textOffset)
```

```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/tenant/devices", params = {"limit"}, method = RequestMethod.GET)
    @ResponseBody
    public TextPageData<Device> getTenantDevices(
            @RequestParam int limit,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String idOffset,
            @RequestParam(required = false) String textOffset)
```

* /tenant/devices, new method, overload method

```
    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/tenant/devices", params = {"deviceName"}, method = RequestMethod.GET)
    @ResponseBody
    public Device getTenantDevice(
            @RequestParam String deviceName)
```

* /customer/{customerId}/devices, parameter added

```
    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/customer/{customerId}/devices", params = {"limit"}, method = RequestMethod.GET)
    @ResponseBody
    public TextPageData<Device> getCustomerDevices(
            @PathVariable("customerId") String strCustomerId,
            @RequestParam int limit,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String idOffset,
            @RequestParam(required = false) String textOffset)
```

```
    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/customer/{customerId}/devices", params = {"limit"}, method = RequestMethod.GET)
    @ResponseBody
    public TextPageData<Device> getCustomerDevices(
            @PathVariable("customerId") String strCustomerId,
            @RequestParam int limit,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String idOffset,
            @RequestParam(required = false) String textOffset)
```

* /devices, new method

```
    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/devices", params = {"deviceIds"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Device> getDevicesByIds(
            @RequestParam("deviceIds") String[] strDeviceIds)
```

* /devices, new method

```
@PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/devices", method = RequestMethod.POST)
    @ResponseBody
    public List<Device> findByQuery(@RequestBody DeviceSearchQuery query)
```

* /devices/types, new method

```
@PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/device/types", method = RequestMethod.GET)
    @ResponseBody
    public List<EntitySubtype> getDeviceTypes()
```

`EntityRelationController.java`
* New controller   

`HttpValidationCallback.java`
* New controller  

`RpcController.java`
* New controller 

`PluginController.java`
* Deleted controller 

`RuleController.java`
* Deleted controller 

`RuleChainController.java`
* New controller 

`TbUrlConstants.java`
* New controller 

`TelemetryController.java`
* New controller 

`UserController.java`

* /user/tokenAccessEnabled, new method

```
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'TENANT_ADMIN')")
    @RequestMapping(value = "/user/tokenAccessEnabled", method = RequestMethod.GET)
    @ResponseBody
    public boolean isUserTokenAccessEnabled()
```

* /user/{userId}/token, new method
```
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'TENANT_ADMIN')")
    @RequestMapping(value = "/user/{userId}/token", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode getUserToken(@PathVariable(USER_ID) String strUserId)
```

* /user, parameter added
```
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody 
    public User saveUser(@RequestBody User user,
            HttpServletRequest request)
```

```
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody 
    public User saveUser(@RequestBody User user,
                         @RequestParam(required = false, defaultValue = "true") boolean sendActivationMail,
                         HttpServletRequest request)
```

* /user/{userId}/activationLink, new method

```
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'TENANT_ADMIN')")
    @RequestMapping(value = "/user/{userId}/activationLink", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public String getActivationLink(
            @PathVariable(USER_ID) String strUserId,
            HttpServletRequest request)
```

git diff --stat release-1.0:application/src/main/java/org/thingsboard/server/controller/UserController.java master:application/src/main/java/org/thingsboard/server/controller/UserController.java

git diff release-1.0:application/src/main/java/org/thingsboard/server/controller/UserController.java master:application/src/main/java/org/thingsboard/server/controller/UserController.java

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