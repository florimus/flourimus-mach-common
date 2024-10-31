import mongoose from "mongoose";
import { getCurrentTime } from "../core/utls/timeUtils";

const Schema = mongoose.Schema;

const commonFieldsSchema = new Schema({
  brandId: { type: String },
  organizationId: { type: String },
  locationId: { type: String },
  channelId: { type: String },
  createdAt: { type: String, default: getCurrentTime() },
  updatedAt: { type: String, default: getCurrentTime() },
  isActive: { type: Boolean, default: false },
  createdBy: { type: String },
  updatedBy: { type: String, default: getCurrentTime() },
  metaStatus: { type: String, default: getCurrentTime() }
}, { _id: false });

export default commonFieldsSchema;
