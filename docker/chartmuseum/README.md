Script to start ChartMuseum.

### Example 
Can e.g. be used to host `homelab/charts/busybox`.
First, start the container
```shell
./start_chartmuseum_container.sh --user <user> --password <password>
```

Then, package the chart
```shell
cd charts/busybox
./package_chart.sh
```
And push it to chartmuseum

```shell
cd package
curl -u <user> --data-binary "@busybox-0.1.0.tgz"
```

Now it get see that it is listed with:
```shell
curl -u chartuser  -X GET http://localhost:8080/api/charts
```


For more information, see the [ChartMuseum GitHub repository](
https://github.com/helm/chartmuseum)

