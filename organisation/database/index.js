const release = "R1.0.0";
const fileDir = `./${release}/imports`

// const mongoose = require("mongoose");
import mongoose from "mongoose";
import { scripts } from "./R1.0.0/imports.js";

const localDbUrl = "mongodb://127.0.0.1:21780/flourimus_common";

const env = process.argv?.[2];
const url = process.argv?.[3];

const MONGO_URI = env === "local" ? localDbUrl : url;

(async () => {
  try {
    await mongoose.connect(MONGO_URI);

    console.info("Connected to MongoDB");
    console.info(`Runnig Scripts for Release: ${release}`);

    await Promise.all(scripts.map(async (each) => await each(mongoose, env)));

    await mongoose.disconnect();
    console.info("Disconnected from MongoDB");
  } catch (error) {
    console.error("Error:", error);
    process.exit(1);
  }
})(env);
