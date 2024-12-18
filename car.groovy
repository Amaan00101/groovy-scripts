// Hello, World! Program
println "Hello, World!"

// List of cars with release years using maps
def cars = [
    [name: "Toyota Corolla", year: 2022],
    [name: "Ford Mustang", year: 2021],
    [name: "Chevrolet Camaro", year: 2020],
    [name: "Tesla Model S", year: 2023],
    [name: "Honda Civic", year: 2022]
]

// Print table header
println "\nCar Models and Their Release Years"
println "| Car Model           | Release Year |"
println "|---------------------|--------------|"

// Loop through the list and print each car's details in table format
cars.each { car ->
    println "| ${car.name.padRight(20)} | ${car.year.toString().padLeft(12)} |"
}
