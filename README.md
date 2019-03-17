# ankuran-wages
<h1>Ankuran wages system</h1>

<h3>Available API</h3>

1. Get all Centres <br/>
<pre>Request :</pre>
<pre>
curl -X GET http://localhost:8080/centres/'
</pre>
<pre>Response :</pre>
<pre>
{
    "centres": [
        {
            "id": 1,
            "name": "Banswara"
        },
        {
            "id": 2,
            "name": "Ratlam"
        }
    ]
}
</pre>

2. Get a Centre by id <br/>
<pre><i>Request template:</i></pre>
<pre>
curl -X GET http://localhost:8080/centres/{id}'
**id** is a path variable denoting the unique centre specific id with allowed values in positive, non-zero and long types   
</pre>
<pre>Request :</pre>
<pre>
curl -X GET http://localhost:8080/centres/1'
</pre>
<pre>Response :</pre>
<pre>
{
    "id": 1,
    "name": "Banswara"
}
</pre>

3. Get all Employees for a Centre <br/>
<pre><i>Request template:</i></pre>
<pre>
curl -X GET http://localhost:8080/centres/{centre-id}/employees/'
**centre-id** is a path variable denoting the unique centre specific id with allowed values in positive, non-zero and long types
</pre>
<pre>Request :</pre>
<pre>
curl -X GET http://localhost:8080/centres/1/employees/'
</pre>
<pre>Response :</pre>
<pre>
{
    "employees": [
        {
            "id": 1,
            "fullName": "Sushil Mittal",
            "mobile": null,
            "timeOfJoining": "2019-02-24T10:20:11.000+0000",
            "husband": null,
            "centre": 1,
            "active": true,
            "outstandingDue": 500.7
        },
        {
            "id": 2,
            "fullName": "Garvit Jain",
            "mobile": "9880199911",
            "timeOfJoining": "2019-03-02T10:20:10.000+0000",
            "husband": null,
            "centre": 1,
            "active": true,
            "outstandingDue": 300.5
        },
        {
            "id": 3,
            "fullName": "Rajat K",
            "mobile": "9207179678",
            "timeOfJoining": "2019-03-03T10:20:10.000+0000",
            "husband": {
                "fullName": "Bhagvaan Jaane",
                "bslEmployeeId": "aadhar-card-number"
            },
            "centre": 1,
            "active": false,
            "outstandingDue": 400.7
        },
        {
            "id": 4,
            "fullName": "Nikita",
            "mobile": "11111111",
            "timeOfJoining": "2019-03-05T10:20:10.000+0000",
            "husband": {
                "fullName": null,
                "bslEmployeeId": "aadhar-card-number"
            },
            "centre": 1,
            "active": false,
            "outstandingDue": null
        }
    ]
}
</pre>

2. Get an Employee for a Centre by id <br/>
<pre><i>Request template:</i></pre>
<pre>
curl -X GET http://localhost:8080/centres/{centre-id}/employees/{employee-id}'
**centre-id** is a path variable denoting the unique centre specific id with allowed values in positive, non-zero and long types
**employee-id** is a path variable denoting the unique employee specific id with allowed values in positive, non-zero and long types
</pre>
<pre>Request :</pre>
<pre>
curl -X GET http://localhost:8080/centres/1/employees/2'
</pre>
<pre>Response :</pre>
<pre>
{
    "id": 2,
    "fullName": "Garvit Jain",
    "mobile": "9880199911",
    "timeOfJoining": "2019-03-02T10:20:10.000+0000",
    "husband": null,
    "centre": 1,
    "active": true,
    "outstandingDue": 300.5
}
</pre>

<h3>Extra Gyan</h3>
Link for API contracts is available <a href="https://github.com/garvitjain314/contracts/tree/master/samples"> here </a> <br/> 
Command to be used to build (in parent directory):<br/>
<pre>mvn clean install -Plocal</pre>
