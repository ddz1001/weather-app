
# Weather API

Generate charts from historical weather data using Open-Meteo, JFreeChart and JFreeSVG.

## Features

- Generate svg-based charts from historical weather data
- Compare averages between different cities from across the world
- Customize chart style and units of measurement, fahrenheit, celsius and kelvin are all supported
- Aggregate averages on a daily, weekly, monthly, or yearly basis
- Easily identify monthly and yearly temperature trends, from data starting as far back as 1965
- Customized svg allows for full-use of browser features, including tooltips for data points

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
There are three primary endpoints for generating charts:

#### `/charting/create`
This endpoint accepts a POST request containing a Chart Request. Upon succesful generation,
a key is returned which can be used to retrieve the generated chart. This key can be used in the other endpoint.

Example of a request:

`POST http://localhost:8080/charting/create`
```json
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
```json
{
    "response-data": {
        "resource-key": "Yjg4MThmZmUtZjI5OS00NWU3LWJiNGQtZjUzYjAwYTUxN2U1.svg"
    },
    "error-data": null
}
```

### `charts/preview/{key}`

This endpoint accepts a GET request, with the desired resource specified in the url. If the resource was found,
the data is returned to the user in the SVG format. If not found, a `404 Not Found` response is returned.

Example of a request:
`GET http://localhost:8080/charts/generatedchartid.svg`

You can also put the url into a browser to view the svg. 

### `charts/download/{key}`

This endpoint accepts a GET request, with the desired resource specified in the url. If the resource was found,
the data is returned to the user in the SVG format. If not found, a `404 Not Found` response is returned.

The `Content-Disposition` header is set, so this endpoint can be used to download the file as
an attachment (through a browser or otherwise).

Example of a request:
`GET http://localhost:8080/charts/generatedchartid.svg`

### JSON Schema

A fully featured chart request can be viewed below, showing the different options:
```json
{
    "chart-details":{
        "chart-interval":"daily",
        "chart-style":"line",
        "unit":"f"
    },

    "cities" : [
        {
            "city-name":"new york",
            "country":"United States",
            "admin-region":"new york"
        },
        {
            "city-name":"berlin",
            "country":"germany"
        },
        {
            "city-name":"Tokyo"
        }
        
    ],

    "date-range":{
        "start-date":"2022-10-01",
        "end-date":"2022-12-07"
    }
}
```

Here, you can see the different options for querying cities, as well as different chart styling options. A more detailed
explanation of these fields can be found below:

#### chart-details

This object specifies how the chart should be styled

```json
"chart-details":{
    "chart-interval":"daily",
    "chart-style":"line",
    "unit":"f"
}
```

| Field            | Description                                                                                                                                                                                                   | Optional                   |
|------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------|
| `chart-interval` | Specifies the interval, a more detailed explanation of intervals is provided below. The following intervals are supported:<br/>-`daily`<br/>-`weekly`<br/>-`monthly`<br/>-`yearly`                            | Yes, defaults to `daily`   |
| `chart-style`    | Specifies the chart rendering style. Charts can be rendered as either `bar` charts (where multiple series are rendered side-by-side) or as `line` charts.                                                     | Yes, defaults to `bar`     |
| `unit`           | Specifies the units to use for temperatures. `fahrenheit`, `celsius` and `kelvin` are supported. You can also shorthand the unit as either `f`, `c`, or `k`, for fahrenheit, celsius and kelvin respectively. | Yes, defaults to `celsius` |

If the `chart-details` object is absent, then a chart using `daily`, `bar`, and `celsius` will be generated.

#### cities

The cities array specifies which cities are to be queried on. Many cities can be included at once, resulting in a chart containing data for all the specified cities.
```json
"cities" : [
    {
        "city-name":"new york",
        "country":"United States",
        "admin-region":"new york"
    },
    {
        "city-name":"berlin",
        "country":"germany"
    },
    {
        "city-name":"Tokyo"
    }
        
]
```

Here, you can see there are 3 levels of granularity for searches. Since many cities throughout the world often share the same name
(or, countries often having many cities with the same name), 3 levels of searching can be done.

Cities are selected by first matching relevant fields. The city with the highest population, matching
the relevant fields, is selected.

An important rule is that you must provide a `country` if you also are providing a `admin-region`.
So this is not allowed:
```json
{
    "city-name":"new york",
    "admin-region":"new york"
}
```

But this is:
```json
{
    "city-name":"new york",
    "country":"United States",
}
```

An explanation of the fields can be found below:

| Field          | Description                                                                                                                                                                                                | Optional |
|----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| `city-name`    | Specifies the city name. It is best to match the spelling and accents if any are present (see below)                                                                                                       | No       |
| `country`      | Specifies the country to search within. The English name of the country should be used. Alternatively, you can use the two-letter ISO code for the country instead (`DE` instead of `Germany` for example) | Yes      |
| `admin-region` | Specifies the administrative region to search within. This could be a province, territory, state, or any kind of top-level administrative area.                                                            | Yes      |


##### Special Characters and Accents
Many cities and administrative regions contain special characters not usually present on standard keyboards.
Such examples could be _Michoacán_ in Mexico, or _La Coruña_ in Spain, or _Region Sjælland_ in Denmark.

Since many keyboards no not have these characters by default, an option is provided to attempt to match spellings not
containing these characters. So one can write _Cancun_ instead of _Cancún_ and achieve the same result. There are limitations to this, and can result in inaccurate results. It is best to use the
full spelling for cities and administrative regions, if possible. 

##### Alternative Fields and Shorthanding

For the _United States_, _Canada_, and _Mexico_ alternative fields
can be provided to shorten the name of administrative regions, as well
as omit the `country` field.

The following can be used in lieu of a `country` + `admin-region` field:

- `usa-state`
- `can-province`
- `mex-state`

An example of each can be seen below:
```json
{
    "city-name":"new york",
    "usa-state":"NY"
},
{
    "city-name":"toronto",
    "can-province":"ON"
},
{
    "city-name":"Oaxaca City",
    "mex-state":"OAX"
}
```

Explanations for these fields are below:

| Field            | Description                                | Accepted values                                                                                        |
|------------------|--------------------------------------------|--------------------------------------------------------------------------------------------------------|
| `usa-state`      | A state or territory of The United States  | U.S. Two-letter state abbreviation, or the full name (`NY` and `New York` are synonymous, for example) |
| `can-province`   | A province or territory of Canada          | Canadian Two-letter province abbreviation, or the full name.                                           |
| `mex-state`      | A state of Mexico                          | Mexican Three-letter state code, or the full-name                                                      |

#### date-range

This object specifies the start and end dates for temperature recording.
An example is provided below:
```json
"date-range":{
    "start-date":"2022-10-01",
    "end-date":"2022-12-07"
}
```

`start-date` and `end-date` and are both **inclusive**, the object, as well as the fields are **required**.

The standard ISO 8601 local date format should be used, `YYYY-MM-DD`. 
<br/>Depending on the indicated `chart-interval`, the date may be adjusted to align with the desired interval width.

Due to the nature of data sourcing, the dates `CURRENT - 5` will not have any data. This is due to an approximetely 5 day delay
in historical weather recording. So it is best to set the `end-date` to at least two-weeks behind the current day.

If a larger interval is used (anything larger than `daily`), then be sure to adjust the `end-date` accordingly, as charts will not generate if it
cannot find data for a given date. More explanation of intervals can be found below.

### Intervals

Intervals define how data should be chunked together and presented to the user. Data is always on a per-day basis,
therefore, if one wants to know the average monthly temperature, the data has to be averaged. To achieve accurate results, 
the start and end dates have to fall at the beginning and end of an interval.

For instance, if I wanted a monthly average over the period of 5 months, the start date should fall at the beginning of the first month, 
and the end date should fall at the end of the given fifth month.

This can be tedious to do manually, so the program will automatically adjust provided dates.

This is so the user doesn't have
to worry about putting in the exact the dates, and allows for quick swapping of intervals, without having to 
manually adjust the dates every time.

The following table describes all of the available intervals, as well as how adjustments are calculated:

| Interval | Description                                   | Chart Label                                                                                               | Provided Value                          | After-Adjustments                       | Notes                                                                               |
|----------|-----------------------------------------------|-----------------------------------------------------------------------------------------------------------|-----------------------------------------|-----------------------------------------|-------------------------------------------------------------------------------------|
| `daily`  | Average temperature on a day-by-day basis     | Date in ISO 8601, ex `2020-01-01`                                                                         | start:`2019-12-25`<br/>end:`2020-01-05` | start:`2019-12-25`<br/>end:`2020-01-05` | No adjustment for day-by-day                                                        |
| `weekly` | Average temperature on a week-by-week basis   | Date with ISO week followed by the year, ex `W12, 2015`. <br/>ISO weeks start on Monday and end on Sunday | start:`2022-11-08`<br/>end:`2022-11-24` | start:`2022-11-07`<br/>end:`2022-11-27` | Start date adjusted to start of the week, end date adjusted to end of week.         |
| `monthy` | Average temperature on a month-by-month basis | Date with month name followed by the year, ex `April, 2011`                                               | start:`1999-01-05`<br/>end:`1999-05-11` | start:`1999-01-01`<br/>end:`1999-05-31` | Start date adjusted to start of the January, end date adjusted to end of May.       |
| `yearly` | Average temperature on a year-by-year basis   | Date with only the year, ex `1978`                                                                        | start:`1979-03-04`<br/>end:`1989-09-07` | start:`1979-01-01`<br/>end:`1989-12-31` | Start date adjusted to January 1st, 1979; end date adjusted to December 31st, 1989. |


### Automatic Styling

For charts with very large datasets (either many cities, or many dates, or both), some styling options are applied automatically.
Category axis labels will be rotated in order to not overcrowd the x-axis. Furthermore, individual labels will be hidden to avoid
overcrowding on the chart itself. 

Line charts also automatically compress the range, this is to prevent the lines from crowding near the top of the chart.

### Chart Features

Charts are generated using SVG. Either Line and Shape charts or Bar charts can be generated.
SVG supports the use of tooltips, so one can hover over each item to see the indicated value.
Charts automatically grow depending on the size of the dataset.

## Examples

There are a number of examples in the `examples` directory at the project root. These examples show various requests, as
well as the resulting svg charts.

## Limitations

This project utilizes Open-Meteo for sourcing both meteorological and geographic information. As
Open-Mateo is intended for educational purposes, requests may be slow, or may time-out for very large
datasets. 

## Acknowledgements

This project uses [Open-Meteo](https://open-meteo.com/) for its weather and geographic sourcing, which in
itself is sourced from [Copernicus](https://www.copernicus.eu/en). Further citations/acknowlegements
can be found in the `ACKNOWLEDGEMENTS` file at the project root.

## Licensing

This project is licensed under GNU GPL 3.0. More information can be found
in the `LICENSE` file at the project root.

## Disclaimer

This project is intended to be used for demonstrative and educational purposes only.
The API provided by Open-Meteo can only support low-volume applications, as their
API is not in real-time.

If one wishes to modify this application to use a high-speed weather sourcing API, feel free to do so in
accordance with the GNU GPL 3.0 license.