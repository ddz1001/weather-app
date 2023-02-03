
# Weather API

Generate charts from historical weather data.

## How to Run
You can either download and compile yourself, or use your preferred IDE to run the project.
The code was developed using IntelliJ, so I would recommend using that, though Eclipse or any
other IDE for Java will work just as well. 

By default, the application will run on port 8080, this can be changed in the `application.yml` file.
To use the endpoints, I would recommend using Postman to create the requests. I would recommend using
Chrome or Edge to view the svg charts, as they offer the highest quality render; however, Postman
can also be used to quickly view the resulting charts. 

## Usage

### Endpoints
There are two primary endpoints for generating charts:

#### `/charting/create`
This endpoint accepts a POST request containing a Chart Request. Upon succesful generation,
a key is returned which can be used to retrieve the generated chart. This key can be used in the other endpoint.

Example of a request:

`POST http://localhost:8080/charting/create`
```
{
    "chart-details":{
        "chart-interval":"daily",
        "chart-style":"line",
        "unit":"f"
    },

    "cities" : [
        {
            "city-name":"new york"
        }
    ],

    "date-range":{
        "start-date":"2022-10-01",
        "end-date":"2022-10-07"
    }
}
```

Example of a response:
```
{
    "response-data": {
        "resource-key": "Yjg4MThmZmUtZjI5OS00NWU3LWJiNGQtZjUzYjAwYTUxN2U1.svg"
    },
    "error-data": null
}
```

#### `charts/{key}`

This endpoint accepts a GET request, with the desired resource specified in the url. If the resource was found,
the data is returned to the user in the SVG format. If not found, a `404 Not Found` response is returned.

Example of a request:
`GET http://localhost:8080/charts/generatedchartid.svg`

You can also put the url into a browser to view the svg. 

### JSON Schema

A fully featured chart request can be viewed below, showing the different options:
```

```

