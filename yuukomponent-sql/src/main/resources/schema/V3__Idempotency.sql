CREATE TABLE components_idempotency (
  component_id BINARY(16) PRIMARY KEY,
  idempotent_access INT NOT NULL,
  FOREIGN KEY component_id REFERENCES components (uuid) ON DELETE CASCADE
);