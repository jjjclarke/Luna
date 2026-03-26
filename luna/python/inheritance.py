class Person:
    def __init__(self, name):
        self.name = name
    
    def get_person(self):
        return "This person is named " + self.name

class Student(Person):
    def __init__(self, name, grade):
        super().__init__(name)
        self.grade = grade
    
    def get_person(self):
        return "This person is named " + self.name + ". Their grade is " + self.grade


john = Person("John")
jane = Student("Jane", "87")

print(john.get_person())
print(jane.get_person())