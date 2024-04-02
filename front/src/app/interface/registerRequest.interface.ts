export interface RegisterRequest {
  lastName: string;
  firstName: string;
  birthDate: Date | null; // Allow Date or null
  address: string;
  email: string;
  password: string;
}
