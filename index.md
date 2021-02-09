## Legs United

Scott Clark | Jamie Roberts | Maciej Kaszynski | Kristoff Liu | Oliver Xie |
Tammy Yeung

![](./Jesus23.png)

### Auber Game Download

- [Auber]()

### Assessment 1 Documentation (pre change report)

- [Requirements](https://jonwadman.github.io/AuberEndeavour/files/Req1.pdf)
- [Architecture](https://jonwadman.github.io/AuberEndeavour/files/Arch1.pdf)
- [Method Selection and Planning](https://jonwadman.github.io/AuberEndeavour/files/Plan1.pdf)
- [Risk Assessment and Mitigation](https://jonwadman.github.io/AuberEndeavour/files/Risk1.pdf)
- [Implementation](https://jonwadman.github.io/AuberEndeavour/files/Impl1.pdf)

### Assessment 1 Documentation (post change report)

- [Requirements](https://drive.google.com/file/d/1iS0GgkKmOyjP0k7lRxF-VgLU9pTw7oBp/view?usp=sharing)
- [Architecture](https://drive.google.com/file/d/1wNm0mpSe4j2HHKR1W2ItOh-7hNYDXBEi/view?usp=sharing)
- [Method Selection and Planning](https://drive.google.com/file/d/1K5z6P7G2P8n-rDMQhpV-Gu4O_dv_Pl2K/view?usp=sharing)
- [Risk Assessment and Mitigation](https://drive.google.com/file/d/1Q_uzhiGF9Y5riW_YWzWfkx2RdxkWiF2m/view?usp=sharing)

### Assessment 2 Documentation

- [Change Report](https://drive.google.com/file/d/1BzfYf5K_QiLJf2lVhhzScECuyxKaQNpk/view?usp=sharing)
- [Implementation](https://drive.google.com/file/d/1sLAVCsaiukEYnw-6DfASEXgL4HwvuRho/view?usp=sharing)
- [Testing]()
- [Continuous Integration](https://drive.google.com/file/d/1f7gNQAREfzvXN-V8OBuMwZOqEbdKxwar/view?usp=sharing)

## Testing

### Pre-Implementation Component Testing

| Test ID | Feature to test and functions that make it work                                                                                                                                                                                           | Description of actions to take                                                            | Expected result                                                                           | Actual result                                   |
| ------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------- |
| PRE1    | Player movement game.actors.characters.Auber#handleMovement<br>Everything from MovementSystem                                                                                                                                             | Input movement keys one at a time                                                         | Player moves                                                                              | Passed.                                         |
| PRE2    | | Input 2 input that are opposite                                                                                                                                                                                                           | The character should stop or choose a direction to move in                                | Passed.                                                                                   |
| PRE3    | | Input 2 inputs that are not opposite.                                                                                                                                                                                                     | The character starts moving diagonally.                                                   | Failed, the team probably changed this so that it would be easier to do edge collisions.  |
| PRE4    | Beaming Hostiles<br>game.actors.characters.Auber#arrest                                                                                                                                                                                   | Arrest and any NPC.                                                                       | The user should be able to arrest them.                                                   | Passed.                                         |
| PRE5    | | With an NPC that is stud still try to arrest them from the furthest as you can.                                                                                                                                                           | The user can arrest the hostile when they are touching the NPC.                           | Passed however to arrest the players top has to be at least at the center of the hostile. |
| PRE6    | | Arrest an NPC that is currently sabotaging a system.                                                                                                                                                                                      | The user should be able to arrest them.                                                   | Passed.                                                                                   |
| PRE7    | Hallucination effect gets removed when entering the infirmary<br>game.screens.PlayScreen#setHallucinate<br>game.actors.characters.Infiltrator#usePower<br>game.screens.PlayScreen#drawHallucinate<br>game.stages.Hud#showHallucinateLabel | Walk into the infirmary from all directions and teleport into it while having the effect. | In all cases the effect should be removed.                                                | Passed.                                         |
| PRE8    | Hostiles ability to boost<br>game.actors.characters.Infiltrator#usePower<br>game.actors.characters.Infiltrator#stopPower<br>game.screens.PlayScreen#updateInfiltrators                                                                    | Follow a hostile and observe the behaviour during and after using a speed boost.          | The hostile randomly boosts and continous at normal speed after some time.                | The hostiles stop moving after using the boost. |
| PRE9    | Hostiles ability to go invisable<br>game.actors.characters.Infiltrator#usePower<br>game.actors.characters.Infiltrator#stopPower<br>game.screens.PlayScreen#updateInfiltrators                                                             | Follow a hostile and observe if the hostile returns to normal affter going invisible      | The hostile go invisible and then retuns to a normal sprite.                              | Passed.                                         |
| PRE10   | Hostiles ability to shapeshift<br>game.actors.characters.Infiltrator#usePower<br>game.actors.characters.Infiltrator#stopPower<br>game.screens.PlayScreen#updateInfiltrators                                                               | Follow a hostile and observe if the hostile returns to normal affter shape shifting.      | The hostile shapeshifts and then retuns to a normal sprite.                               | Passed.                                         |
| PRE11   | Edge detection<br>game.systems.Collider<br>game.systems.MovementSystem#up,#down#right,#left<br>game.actors.characters.Character#checkCollision                                                                                            | Walk at multiple walls vertically.                                                        | The wall stops you                                                                        | Passed.                                         |
| PRE12   || Walk into a system.                                                                                                                                                                                                                       | Passed.                                                                                   |
| PRE13   || Walk into a wall by repetedly walking into it vertically                                                                                                                                                                                  | Passed.                                                                                   |
| PRE14   || Walk into a wall by repetedly walking into it Horizontally                                                                                                                                                                                | Passed.                                                                                   |
| PRE15   || Walk into a wall by going horizontally then vertically.                                                                                                                                                                                   | Failed.                                                                                   |
| PRE16   || Walt at multiple walls horizontally                                                                                                                                                                                                       | Passed.                                                                                   |

### Integration Testing

#### Saving and Loading

| Test ID | Feature to test    | Description of actions to take                                                                         | Expected result                                                                                          | Actual result |
| ------- | ------------------ | ------------------------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------------------------- | ------------- |
| INTSAV1 | Saving and Loading | Create a save where an NPC is using an ability and load the save.                                      | The NPC’s previous status should be loaded.                                                              | Passed.       |
| INTSAV2 | Loading            | Load a save where the player has an active ability.                                                    | The player's status should be loaded.                                                                    | Passed.       |
| INTSAV3 | Loading            | Create a save where the difficult is set the highest, then load the save and count the number of NPC’s | The number of NPC’s should be the same as the number of NPC’s there should be on the highest difficulty. | Passed.       |

#### Powers

| INTPOW1 | Boost | Try to get through the wall using a boost powerup. | The wall stops you.                          | Passed. |
| ------- | ----- | -------------------------------------------------- | -------------------------------------------- | ------- |
| INTPOW2 | Boost | Try picking up a powerup while boosting            | The player shoulddn't be able to pick it up. | Passed. |

### Component Testing

| Test ID | Feature to test      | Description of actions to take                        | Expected result                                                         | Actual result |
| ------- | -------------------- | ----------------------------------------------------- | ----------------------------------------------------------------------- | ------------- |
| COMP1   | Saving & Loading     | Save a game and load it                               | The game save and you can load it.                                      | Passed.       |
| COMP2   | Loading              | Try loading wihtout selecting a save,                 | The game shouldn't allow you to load.                                   | Passed.       |
| COMP3   | Difficulty           | Change difficulty and count how many NPC’s there are. | The higher the difficulty the more NPCs there should be.                | Passed.       |
| COMP4   | Powerups             | Pick up a freeze power up.                            | All NPC freeze.                                                         | Passed.       |
| COMP5   | Powerups             | Pick up a highlight power up.                         | The Infultrators get highlighted for the player.                        | Passed.       |
| COMP6   | Powerups             | Pick up an immunity power up.                         | The character becomes immune.                                           | Passed.       |
| COMP7   | Powerups             | Pick up a speed power up.                             | You're character speeds up.                                             | Passed.       |
| COMP8   | Powerups             | Pick up a teleport power up.                          | A teleport screen apears.                                               | Passed.       |
| COMP9   | Powerups             | Pick up a power up while having an active             | The player shouldn't be able to pick up more than 1 power up at a time. | Passed.       |
| COMP10  | Beaming Non-hostiles | Arrest a non-hostile.                                 | The game shouldn’t arrest the non-hostile                               | Passed.       |

## Traceability Matrix

| Test ID | Requirement                              | Passed  |
| ------- | ---------------------------------------- | ------- |
| PRE1    | UR\_MOVE                                 | Passed. |
| PRE2    |UR\_MOVE | Passed.                                  |
| PRE3    |UR\_MOVE| Passed.                                  |
| PRE4    | UR\_INFILTRATORS UR\_CITIZENS UR\_ARREST | Passed. |
| PRE5    |UR\_INFILTRATORS UR\_CITIZENS UR\_ARREST| Passed.                                  |
| PRE6    |UR\_INFILTRATORS UR\_CITIZENS UR\_ARREST| Passed.                                  |
| PRE7    | UR\_HOSTILE\_ABILITIES                   | Passed. |
| PRE8    |UR\_HOSTILE\_ABILITIES| Passed.                                  |
| PRE9    |UR\_HOSTILE\_ABILITIES| Passed.                                  |
| PRE10   |UR\_HOSTILE\_ABILITIES| Passed.                                  |
| PRE11   | UR\_FOUR\_ROOMS UR\_MOVE                 | Passed. |
| PRE12   | UR\_FOUR\_ROOMS UR\_MOVE| Passed.                                  |
| PRE13   |UR\_FOUR\_ROOMS UR\_MOVE| Passed.                                  |
| PRE14   |UR\_FOUR\_ROOMS UR\_MOVE| Passed.                                  |
| PRE15   |UR\_FOUR\_ROOMS UR\_MOVE| Passed.                                  |
| PRE16   |UR\_FOUR\_ROOMS UR\_MOVE| Passed.                                  |
| INTSAV1 | UR\_SAVE                                 | Passed. |
| INTSAV2 | UR\_SAVE| Passed.                                  |
| INTSAV3 |UR\_SAVE| Passed.                                  |
| INTPOW1 | UR\_AUBER\_ABILITIES                     | Passed. |
| INTPOW2 |UR\_AUBER\_ABILITIES| Passed.                                  |
| COMP1   | UR\_SAVE                                 | Passed. |
| COMP2   | UR\_SAVE| Passed.                                  |
| COMP3   | UR\_LEVEL                                | Passed. |
| COMP4   | UR\_AUBER\_ABILITIES                     | Passed. |
| COMP5   | UR\_AUBER\_ABILITIES | Passed.                                  |
| COMP6   | UR\_AUBER\_ABILITIES| Passed.                                  |
| COMP7   | UR\_AUBER\_ABILITIES| Passed.                                  |
| COMP8   | UR\_AUBER\_ABILITIES | Passed.                                  |
| COMP9   |UR\_AUBER\_ABILITIES | Passed.                                  |
| COMP10  | UR\_CITIZENS                             | Passed. |
