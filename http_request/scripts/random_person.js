let firstName = "Christian";
let lastName = "Bauer";
let birthDate = "1988-06-23"; // birthDates are usually static
let city = "Distrito Federal";
let state = "Brasilia";
let country = "BR";
let neighborhood = "Centro"
let street = "Rua Jacuí";
let number = 356;
let zipCode = "0000-000";

client.global.set("personJson", JSON.stringify({
    firstName,
    lastName,
    birthDate,
    address: {
        city,
        state,
        country,
        neighborhood,
        street,
        number,
        zipCode,
    }
}));