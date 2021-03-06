const _ = require('lodash');

var Yatzy = function () {
}

Yatzy.chance = function (d1, d2, d3, d4, d5) {
    return _.sum([d1, d2, d3, d4, d5]);
}

Yatzy.yatzy = function (d1, d2, d3, d4, d5) {
    var sizeOfUniqElements = _.chain([d1, d2, d3, d4, d5])
        .uniq()
        .size()
        .value();
    return sizeOfUniqElements === 1 ? 50 : 0;
}


function sumSameDices (seekedNumber, d1, d2, d3, d4, d5) {
    return _.chain([d1, d2, d3, d4, d5])
        .filter(function (dice) {
            return dice === seekedNumber;
        })
        .size()
        .value() * seekedNumber;
}

Yatzy.ones = function (d1, d2, d3, d4, d5) {
    return sumSameDices(1, d1, d2, d3, d4, d5)
}

Yatzy.twos = function (d1, d2, d3, d4, d5) {
    return sumSameDices(2, d1, d2, d3, d4, d5)
}

Yatzy.threes = function (d1, d2, d3, d4, d5) {
    return sumSameDices(3, d1, d2, d3, d4, d5)
}

Yatzy.fours = function (d1, d2, d3, d4, d5) {
    return sumSameDices(4, d1, d2, d3, d4, d5)
}

Yatzy.fives = function (d1, d2, d3, d4, d5) {
    return sumSameDices(5, d1, d2, d3, d4, d5)
}

Yatzy.sixes = function (d1, d2, d3, d4, d5) {
    return sumSameDices(6, d1, d2, d3, d4, d5)
}

Yatzy.pair = function (d1, d2, d3, d4, d5) {
    let elementsWithRedundancy = _.chain([d1, d2, d3, d4, d5])
        .countBy(Math.floor)
        .value();
    let max = _.chain(_.keys(elementsWithRedundancy))
        .filter(function (e) {
            return elementsWithRedundancy[e] > 1
        })
        .max()
        .value();
    return parseInt(max) ? (parseInt(max) * 2) : 0;
}

Yatzy.twoPair = function (d1, d2, d3, d4, d5) {
    let elementsWithRedundancy = _.chain([d1, d2, d3, d4, d5])
        .countBy(Math.floor)
        .value();
    let pairs = _.chain(_.keys(elementsWithRedundancy))
        .filter(function (e) {
            return elementsWithRedundancy[e] > 1
        })
        .map(function (e) {
            return parseInt(e)
        })
        .value()
    return pairs.length === 2 ? _.sum(pairs) * 2 : 0
}

function nOfAKind (n, d1, d2, d3, d4, d5) {
    let elementsWithRedundancy = _.chain([d1, d2, d3, d4, d5])
        .countBy(Math.floor)
        .value();
    let nKinds = _.chain(_.keys(elementsWithRedundancy))
        .filter(function (e) {
            return elementsWithRedundancy[e] >= n
        })
        .map(function (e) {
            return parseInt(e)
        })
        .value();
    return nKinds.length ? nKinds[0] * n : 0
}

Yatzy.threeOfAKind = function (d1, d2, d3, d4, d5) {
    return nOfAKind(3, d1, d2, d3, d4, d5)
}

Yatzy.fourOfAKind = function (d1, d2, d3, d4, d5) {
    return nOfAKind(4, d1, d2, d3, d4, d5)
}

Yatzy.smallStraight = function (d1, d2, d3, d4, d5) {
    return _.isEqual([1, 2, 3, 4, 5], [d1, d2, d3, d4, d5].sort()) ? 15 : 0
}

Yatzy.largeStraight = function (d1, d2, d3, d4, d5) {
    return _.isEqual([2, 3, 4, 5, 6], [d1, d2, d3, d4, d5].sort()) ? 20 : 0
}

Yatzy.fullHouse = function (d1, d2, d3, d4, d5) {
    let areAllDicesSame = _.chain([d1, d2, d3, d4, d5]).uniq().size() === 1;
    if (areAllDicesSame) return 0;
    let isTwoAndTreeKind = nOfAKind(3, d1, d2, d3, d4, d5) > 0
        && nOfAKind(2, d1, d2, d3, d4, d5) > 0;
    return isTwoAndTreeKind ? this.chance(d1, d2, d3, d4, d5) : 0;
}

module.exports = Yatzy;


