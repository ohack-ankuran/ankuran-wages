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
            "mobileNo": null,
            "dateOfJoining": "2019-02-24T10:20:11.000+0000",
            "husband": null,
            "centre": 1,
            "active": true,
            "name": {
                "lastName": null,
                "firstName": null,
                "fullName": "Sushil Mittal"
            }
        },
        {
            "id": 2,
            "mobileNo": "9880199911",
            "dateOfJoining": "2019-03-02T10:20:10.000+0000",
            "husband": null,
            "centre": 1,
            "active": true,
            "name": {
                "lastName": null,
                "firstName": null,
                "fullName": "Garvit Jain"
            }
        },
        {
            "id": 3,
            "mobileNo": "9207179678",
            "dateOfJoining": "2019-03-03T10:20:10.000+0000",
            "husband": {
                "name": {
                    "lastName": null,
                    "firstName": null,
                    "fullName": "Bhagvaan Jaane"
                },
                "bslEmployeeId": "aadhar-card-number"
            },
            "centre": 1,
            "active": false,
            "name": {
                "lastName": null,
                "firstName": null,
                "fullName": "Rajat K"
            }
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
    "mobileNo": "9880199911",
    "dateOfJoining": "2019-03-02T10:20:10.000+0000",
    "husband": null,
    "centre": 1,
    "active": true,
    "name": {
        "lastName": null,
        "firstName": null,
        "fullName": "Garvit Jain"
    }
}
</pre>

<h3>Extra Gyan</h3>
Command to be used to build (in parent directory):<br/>
<pre>mvn clean install -Plocal</pre>
