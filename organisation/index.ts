import { Elysia } from "elysia";
import { apollo } from "@elysiajs/apollo";
import definitions from "./app/definitions";
import mongoose from "mongoose";
import { eurekaClient } from "./eurekaClient";

const port = process.env.PORT!;

mongoose.connect(process.env.MONGO_URI!).then(() => {
  new Elysia()
    .use(
      apollo({
        path: "/graphql",
        enablePlayground: true,
        ...definitions,
      })
    )
    .get("/actuator/:info", () => ({ status: "UP" }))
    .get("/info", () => ({
      name: process.env.APP_NAME || "Organisation",
      version: process.env.APP_VERSION || "1.0.0",
      description: "Organisation service info",
    }))
    .listen(port);
  console.log(`🚀 Server ready at http://localhost:${port}/graphql`);
  eurekaClient.start();

  process.on('SIGINT', () => {
    eurekaClient.stop();
    process.exit();
  })
});