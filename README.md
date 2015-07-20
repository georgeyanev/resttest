# resttest
Simple jax-rs client only test with XML messages. 
The tests are failing because jersey-client does not get/use the registered ContextResolver

Execute tests with 

    mvn test
