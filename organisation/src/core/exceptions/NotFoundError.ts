export default class NotFoundError extends Error {
    status: number = 400;
    code: string = "NOT_FOUND";
    stacktrace: string[] = [];
  
    constructor(message: string) {
      super(message);
      this.status = 404;
      Object.setPrototypeOf(this, NotFoundError.prototype);
    }
  }
  