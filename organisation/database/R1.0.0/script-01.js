import { isDelta, deltaSuccess } from "../../database/delta.js";

const scriptId = "R1.0.0/script-01.js";

const script_01 = async (mongoose, env = "local") => {
  const isRunable = await isDelta(mongoose, scriptId);

  if (!isRunable) {
    console.log(`skipping script: ${scriptId}`);
    return;
  }

  const collection = mongoose.connection.collection("flourimus_systems");

  if (env) {
    await collection.insertMany([
      {
        _id: "1",
        name: "authentication-layer-settings",
        code: "AuthenticationLayerSettings",
        defaultConfigurations: {
          organizationId: true,
          brandId: true,
          locationId: true,
          channelId: true,
        },
        organizationId: "1",
        brandId: "1",
        locationId: "1",
        channelId: "1",
        createdAt: "2024-01-10T10:00:00.000Z",
        updatedAt: "2024-05-20T10:00:00.000Z",
        isActive: true,
        createdBy: "script@R1.0.0-01",
        updatedBy: "script@R1.0.0-01",
        metaStatus: "active",
      },
    ]);
    console.log("Dummy user's inserted successfully.");
  }
  if (env === "local") {
  }
  if (env === "uat") {
  }
  if (env === "prod") {
  }

  await deltaSuccess(mongoose, scriptId);
};

export default script_01;
