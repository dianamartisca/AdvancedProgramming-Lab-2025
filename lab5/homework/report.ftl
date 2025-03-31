<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Report</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Image Report</h1>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Date</th>
                <th>Tags</th>
                <th>File Path</th>
            </tr>
        </thead>
        <tbody>
            <#list images as image>
                <tr>
                    <td>${image.name()}</td>
                    <td>${image.date()}</td>
                    <td>${image.tags()?join(", ")}</td>
                    <td>${image.filePath()}</td>
                </tr>
            </#list>
        </tbody>
    </table>
</body>
</html>
