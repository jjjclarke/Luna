class Character:
    def __init__(self, name, health, attack):
        self.name = name
        self.health = health
        self.attack = attack
    
    def is_alive(self):
        # return a bool
        return self.health > 0
    
    def hit(self, target):
        print(self.name + " attacks " + target.name)
        target.health = target.health - self.attack


class Player(Character):
    def heal(self):
        print(self.name + " heals!")
        self.health = self.health + 5


# ================================

player = Player("James", 30, 6)
enemy = Character("Sentinel", 20, 5)

while player.is_alive() and enemy.is_alive():
    player.hit(enemy)
    
    if enemy.is_alive():
        enemy.hit(player)
    
    if player.health < 10:
        player.heal()
    
    print("Player's HP: ")
    print(player.health)
    print("Enemy's HP: ")
    print(enemy.health)
    print("-- Next Turn --")

if player.is_alive():
    print("You win!")
else:
    print("You lose!")