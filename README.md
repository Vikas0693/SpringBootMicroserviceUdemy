DIFF b/w EAGER AND LAZY FETCHING IN SPRING BOOT CONTEXT
1)in spring when req. comes it opens OSIV(open session in view) which opens hibernate session and closes it after sending response
2)bcoz of 1), if there is lazy fetching just like Post have lazy fetch user, so even in retrieveUserPosts method  in UserJPAResource
'user.get().getPosts().get(0)' will successfully fetch User object also because session is open.
3)Usually when we implement session manually then on lazy fetch , lazy object is fetched if session is open but here session is always open hence no benefit of using lazy
4)to disable OSIV in spring data we used 'spring.jpa.open-in-view=false' in application properties. 