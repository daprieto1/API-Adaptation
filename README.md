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

[Thingsboard GitHub repository](https://github.com/thingsboard/thingsboard/)

### Zetta 

Zetta is an open source, API-first, Node.js-based platform for the Internet of Things. To learn more about Zetta now, jump to Introduction to Zetta on the Zetta doc wiki.

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

-----------

#### v1.5.0 - v1.4.0

**v1.5.0 commit:** 5a1bc56aad75fb63ab3648c700bc8f2d6041c889

**v1.4.0 commit:** 0129dc74ea88d74c71606893c24be3f7332dd453

|||
|-|-|
| files changed | 6     |
| insertions    | 34    |
| deletetions   | 32    |

-----------

#### v1.5.0 - v1.0.0

**v1.5.0 commit:** 5a1bc56aad75fb63ab3648c700bc8f2d6041c889

**v1.0.0 commit:** 0a1c176cc697df9b555b1aecd51d31cf8d44274f

|||
|-|-|
| files changed | 17     |
| insertions    | 944    |
| deletetions   | 189    |




