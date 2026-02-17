## AI Tools Used

- GitHub Copilot

## How they are used

- Copilot used to generate JavaDoc documentation for extracted 12 subcommand classes, 1 abstract command class,
  and 1 general `AddTask` command class. This was done during the `A-CodeQuality` and `BCD-Extension` (`C-Undo`) increments.
- Copilot used to generate a summary of the PR as a comment in the `A-CodeQuality` increment, then edited manually
  to include any details that were missed out by Copilot

## Observations

- Copilot was effective in generating JavaDoc documentation for similar classes,
  as it seemed to quickly recognise the patterns in the documentation
  of similar classes, which sped up the repetitive and mundane process of writing
  documentations of similar classes.

- In the generation of the PR comment, summarisation of code changes tend to miss out on certain minor details,
  perhaps due to large context size when major changes are made in one go

  Overall time saved: Hours perhaps
