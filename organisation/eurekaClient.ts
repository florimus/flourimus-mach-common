import { Eureka } from "eureka-js-client";

const serviceName = process.env.EUREKA_SERVICE_NAME || "elysia-app";
const port: number = Number(process.env.PORT)!;

export const eurekaClient = new Eureka({
  instance: {
    instanceId: `${serviceName}-${port}`,
    hostName: "localhost",
    app: serviceName,
    ipAddr: "127.0.0.1",
    port: {
      "$": port,
      "@enabled": true,
    },
    vipAddress: serviceName,
    secureVipAddress: serviceName,
    status: "UP",
    dataCenterInfo: {
      "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
      name: "MyOwn",
    },
  },
  eureka: {
    host: 'localhost',
    port: 8761,
    servicePath: '/eureka/apps/'
  }
});
