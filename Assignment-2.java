package executableFiles;
import static io.restassured.RestAssured.*;
import java.util.List;
import java.util.Scanner;

import io.restassured.response.Response;

public class WeatherReport 
{
	public static void main(String[] args) 
	{
		baseURI = "https://samples.openweathermap.org";
		Response res = when().get("/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22");
		
		//To Find the values present in the array
		//List<Object> reports = res.jsonPath().get("list");
		//System.out.println("Size -----> "+reports.size());
		
		// Date and Time
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Please Enter the Date in the format of 2019-03-27");
		String date = sc1.next();
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Please Enter the Time in the format of 18:00:00");
		String time = sc2.next();
		String expected = date+" "+time;
		
		Boolean flag = true;
		for(int i=0; i<96; i++)
		{
			String actual = res.jsonPath().get("list["+i+"].dt_txt");
			if(actual.equals(expected))
			{
				// Remote Functions
				Scanner scanner = new Scanner(System.in);
		        int inputValue;

		        do 
		        {
		            System.out.print("Please Enter the Remote Number for Temp press 1, for Speed press 2, for Quit press 0 : ");
		            inputValue = scanner.nextInt();

		            // Perform any necessary operations with the input value here
		            System.out.println();
		            if(inputValue==3)
					{
						// Get the Pressure
		            	String pressure = res.jsonPath().get("list["+i+"].main.pressure").toString();
		            	System.out.println("The Pressure is: "+pressure);
					}
					else if(inputValue==2)
					{
						// Get the Wind Speed
						String speed = res.jsonPath().get("list["+i+"].wind.speed").toString();
						System.out.println("The Wind Speed is: "+speed);
					}
					else if(inputValue==1)
					{
						// Get the Temperature
						String temp = res.jsonPath().get("list["+i+"].main.temp").toString();
						System.out.println("The Temperature is: "+temp);
					}
		        } 
		        while (inputValue != 0);
		        flag = false;
		        scanner.close();
				
				// loop termination 
				if(flag==false)
				break;
			}
		}
	

	}
	
}
