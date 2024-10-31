import NotFoundError from "../core/exceptions/NotFoundError";
import settingsRepository from "../repository/settingsRepository";

export const getAuthenticationLayerSettingsByVitals = async (vitals: AuthenticationLayerSettingsByVitalsRequest) => {
    const settings = await settingsRepository.getSettingsByNameAndCode(vitals);
    if(!settings) {
        throw new NotFoundError("settings not found");
    }
    return settings?.defaultConfigurations
}
