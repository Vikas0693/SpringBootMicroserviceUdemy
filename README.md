DIFF b/w EAGER AND LAZY FETCHING IN SPRING BOOT CONTEXT
1)in spring when req. comes it opens OSIV(open session in view) which opens hibernate session and closes it after sending response
2)bcoz of 1), if there is lazy fetching just like Post have lazy fetch user, so even in retrieveUserPosts method  in UserJPAResource
'user.get().getPosts().get(0)' will successfully fetch User object also because session is open.
3)Usually when we implement session manually then on lazy fetch , lazy object is fetched if session is open but here session is always open hence no benefit of using lazy
4)to disable OSIV in spring data we used 'spring.jpa.open-in-view=false' in application properties.

Challenges in Microservices
1)Bounded context - which part of application is handled by which service
2)Configuration Management - to configure 100s of services
3)Dynamic scale up and down - 
4)Visibility - out of 10 microservices which one caused the error to user by tracing logs to centralized place
5)Pack of Cards (Fault Tolerence) - how to make sure that one service in app. does not bring the whole system down  

Tackling Challenges using different components in spring cloud
1)naming server - eureka
2)client side load balancing - ribbon
3)Easier Rest client - Feign
4)Tracing - zipkin distributed tracing
5)Analytics,Logging,Monitoring - Netflix Zuul api gateway
6)Fault tolerance - Hystrix

Advantages Of Microservices
1)Not Language boundation that is adaptation of new technology
2)Dynamic scaling
3)Faster Release Cycles	- bring new features to market