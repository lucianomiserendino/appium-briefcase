Feature: Verify Connection To DB Can Be Established

  @db_Check
  Scenario: Verify Connection To Informix
    And Verify DB Informix Connection Is Established
      | courtId |
      | test    |
