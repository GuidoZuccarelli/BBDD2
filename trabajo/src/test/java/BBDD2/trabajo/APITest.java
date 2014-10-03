package BBDD2.trabajo;


import org.json.simple.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.jayway.restassured.builder.MultiPartSpecBuilder;
import com.jayway.restassured.specification.MultiPartSpecification;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITest {

	@Before
	public void setUp(){
		post("/rest/utils");
	}
	
	@After
	public void tearDownTheWall(){
		delete("/rest/utils");
	}

}
