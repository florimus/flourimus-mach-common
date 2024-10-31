import mongoose from "mongoose";
import commonFieldsSchema from "./CommonSchema";

const Schema = mongoose.Schema;

export const systemSchema = new Schema({
  name: {
    type: String,
    required: true
  },
  code: {
    type: String,
    required: true
  },
  defaultConfigurations: {
    type: Schema.Types.Mixed,
    required: true
  }
});

systemSchema.add(commonFieldsSchema);

const System = mongoose.model("flourimus_systems", systemSchema);

export default System;
