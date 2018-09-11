# Auto1 task

Solution relies on selenium hub running locally.

Easiest way to set up such test env is to use selenium grid contained inside docker.
This way also assures compatibility between selenium java bindings and selenium server and browser version. 

```sh
$ docker run -d --network host --name selenium-all-in-one selenium/standalone-chrome-debug:3.14.0-dubnium
```
Requirements

  - Java 1.8
  - MVN (tested on 3.5.2)
  - docker (tested on 18.06.1-ce)
  - localhost port 4444 available

Happy testing!
