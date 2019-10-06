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

function nOfAkind (n, d1, d2, d3, d4, d5) {
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
    return nOfAkind(3, d1, d2, d3, d4, d5)
}

Yatzy.fourOfAKind = function (d1, d2, d3, d4, d5) {
    return nOfAkind(4, d1, d2, d3, d4, d5)
}

Yatzy.smallStraight = function (d1, d2, d3, d4, d5) {
    var tallies;
    tallies = [0, 0, 0, 0, 0, 0, 0]
    tallies[d1 - 1] += 1;
    tallies[d2 - 1] += 1;
    tallies[d3 - 1] += 1;
    tallies[d4 - 1] += 1;
    tallies[d5 - 1] += 1;
    if (tallies[0] == 1 &&
        tallies[1] == 1 &&
        tallies[2] == 1 &&
        tallies[3] == 1 &&
        tallies[4] == 1)
        return 15;
    return 0;
}

Yatzy.largeStraight = function (d1, d2, d3, d4, d5) {
    var tallies;
    tallies = [0, 0, 0, 0, 0, 0, 0, 0];
    tallies[d1 - 1] += 1;
    tallies[d2 - 1] += 1;
    tallies[d3 - 1] += 1;
    tallies[d4 - 1] += 1;
    tallies[d5 - 1] += 1;
    if (tallies[1] == 1 &&
        tallies[2] == 1 &&
        tallies[3] == 1 &&
        tallies[4] == 1
        && tallies[5] == 1)
        return 20;
    return 0;
}

Yatzy.fullHouse = function (d1, d2, d3, d4, d5) {
    var tallies;
    var _2 = false;
    var i;
    var _2_at = 0;
    var _3 = false;
    var _3_at = 0;


    tallies = [0, 0, 0, 0, 0, 0, 0, 0];
    tallies[d1 - 1] += 1;
    tallies[d2 - 1] += 1;
    tallies[d3 - 1] += 1;
    tallies[d4 - 1] += 1;
    tallies[d5 - 1] += 1;

    for (i = 0; i != 6; i += 1)
        if (tallies[i] == 2) {
            _2 = true;
            _2_at = i + 1;
        }

    for (i = 0; i != 6; i += 1)
        if (tallies[i] == 3) {
            _3 = true;
            _3_at = i + 1;
        }

    if (_2 && _3)
        return _2_at * 2 + _3_at * 3;
    else
        return 0;
}

module.exports = Yatzy;


