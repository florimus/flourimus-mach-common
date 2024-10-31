import { Elysia } from "elysia";
import { apollo } from "@elysiajs/apollo";
import definitions from "./app/definitions";
import mongoose from "mongoose";

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
    .listen(port);
  console.log(`🚀 Server ready at http://localhost:${port}/graphql`);
});
