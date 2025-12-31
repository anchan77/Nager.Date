# Nager.Date - Java Port

Java port of the Nager.Date holiday calculation library.

Source: https://github.com/anchan77/Nager.Date.git

## Project Structure

This is a Maven multi-module project:

- **nager-date-core**: Core holiday calculation library (framework-independent)
- Additional modules (console, API) will be added in future milestones

## Requirements

- Java 17 or higher
- Maven 3.6+

## Building

```bash
mvn clean install
```

## Compilation Status

All core domain models have been migrated and compile successfully with Java 17.

### Completed Components

- CountryCode enum (249 ISO 3166-1 ALPHA-2 country codes)
- Core models:
  - Holiday - Public holiday data model
  - HolidaySpecification - Internal specification model
  - HolidayTypes - Flags enum for categorizing holidays
  - HolidaySources - Holiday source classification
  - ObservedRuleSet - Date shifting rules for weekends
  - Month, Occurrence, DateSearchDirection, LicenseCheckStatus - Supporting enums

## Design Decisions

### HolidayTypes Flags Enum
Uses EnumSet<HolidayTypes> for type safety with utility methods for bitmask conversion to maintain C# compatibility.

### ObservedRuleSet Functions
Uses Function<LocalDate, LocalDate> to represent date transformation rules for each day of the week.

## Next Steps

- Implement public facades (HolidaySystem, WeekendSystem)
- Migrate provider infrastructure
- Port religious providers (Catholic, Orthodox)
- Implement country-specific providers
- Add comprehensive test suite
