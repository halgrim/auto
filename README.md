# Auto1 task

Solution relies on selenium hub running locally.

Easiest way to set up such test env is to use selenium grid contained inside docker.
This way also assures compatibility between selenium java bindings and selenium server and browser version. 

```sh
$ docker run -d --network host --name selenium-all-in-one selenium/standalone-chrome-debug:3.14.0-dubnium
```

Happy testing!