const eurekaServerUrl =
  process.env.EUREKA_SERVER_URL || "http://localhost:8761/eureka/apps";
const serviceName = process.env.EUREKA_SERVICE_NAME || "elysia-app";
const port = process.env.PORT!;

/**
 * Registers the current process as a service with the specified Eureka server.
 * @throws An error is thrown if registration fails.
 */
export async function registerWithEureka() {
  const instance = {
    instance: {
      instanceId: `${serviceName}-${port}`,
      hostName: "localhost",
      app: serviceName,
      ipAddr: "127.0.0.1",
      port: {
        $: port,
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
  };

  try {
    const response = await fetch(`${eurekaServerUrl}/${serviceName}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(instance),
    });

    if (response.ok) {
      console.log(`Successfully registered with Eureka as ${serviceName}`);
    } else {
      console.error("Error registering with Eureka:", response.statusText);
    }
  } catch (error) {
    console.error("Error registering with Eureka:", error);
  }
}
