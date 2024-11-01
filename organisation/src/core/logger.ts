export class Logger {

    /**
     * Extracts the file name and line number from the stack trace of the current location.
     * The extracted information is in the format of "file:line".
     * If the extraction fails, the returned value will be "unknown location".
     * @returns The file name and line number of the current location.
     */
    private static getCallerInfo() {
        const stack = new Error().stack?.split('\n')[3]; // Get the 4th line of the stack trace (the caller)
        if (stack) {
            const match = stack.match(/at (.+):(\d+):(\d+)/); // Match file name and line number
            if (match) {
                const fileName = match[1].split('/').pop(); // Extract the file name from the path
                const lineNumber = match[2]; // Extract the line number
                return `${fileName}:${lineNumber}`;
            }
        }
        return 'unknown location';
    }

    /**
     * Log an info message. The message will be prefixed with the given uuid and the location of the caller.
     * @param uuid The uuid to prefix the message with
     * @param message The info message to log
     */
    public static info(uuid: string, message: string) {
        const location = Logger.getCallerInfo();
        console.info(`[${uuid}]: ${location}: ${message}`);
    }

    /**
     * Log a warning message. The message will be prefixed with the given uuid and the location of the caller.
     * @param uuid The uuid to prefix the message with
     * @param message The warning message to log
     */
    public static warn(uuid: string, message: string) {
        const location = Logger.getCallerInfo();
        console.warn(`[${uuid}]: ${location}: ${message}`);
    }

    /**
     * Log an error message. The message will be prefixed with the given uuid and the location of the caller.
     * @param uuid The uuid to prefix the message with
     * @param message The error message to log
     */
    public static error(uuid: string, message: string) {
        const location = Logger.getCallerInfo();
        console.error(`[${uuid}]: ${location}: ${message}`);
    }
}