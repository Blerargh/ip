## AI Tools Used

- GitHub Copilot

## How they are used

- Copilot used to auto-complete JavaDoc documentation for extracted 12 subcommand classes, 1 abstract command class,
  and 1 general `AddTask` command class, after manually writing the documentation for a single command class.
  This was done during the `A-CodeQuality` and `BCD-Extension` (`C-Undo`) increments.
- Copilot used to generate a summary of the PR as a comment in the `A-CodeQuality` increment, then edited manually
  to include any details that were missed out by Copilot
- Copilot used to auto-complete JUnit tests for the `assertValidInput()` public method in concrete command classes
  which have existing validity assertion implementations, after manually writing the tests for a single test class.
  This is done in the `A-MoreTesting` increment, then edited manually to include any details that were missed out by
  Copilot

## Observations

- Copilot was effective in auto-completing JavaDoc documentation for similar classes,
  as it seemed to quickly recognise the patterns in the documentation or JUnit test methods
  of similar classes, which sped up the mundane process of writing repetitive code or documentation
  for multiple similar classes implementing similar assertion checks.

- In the generation of the PR comment, summarisation of code changes tend to miss out on certain minor details,
  perhaps due to large context size when major changes are made in one go

  Overall time saved: Easily hours of time, as the process of writing documentation and
  tests for multiple similar classes is very time-consuming,
