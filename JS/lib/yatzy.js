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

Yatzy.two_pair = function (d1, d2, d3, d4, d5) {
    var counts = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    counts[d1 - 1]++;
    counts[d2 - 1]++
    counts[d3 - 1]++
    counts[d4 - 1]++;
    counts[d5 - 1]++;
    var n = 0;
    var score = 0;
    for (i = 0; i < 6; i += 1)
        if (counts[6 - i - 1] >= 2) {
            n++;
            score += (6 - i);
        }
    if (n == 2)
        return score * 2;
    else
        return 0;
}

Yatzy.four_of_a_kind = function (_1, _2, d3, d4, d5) {
    var tallies;
    tallies = [0, 0, 0, 0, 0, 0, 0, 0]
    tallies[_1 - 1]++;
    tallies[_2 - 1]++;
    tallies[d3 - 1]++;
    tallies[d4 - 1]++;
    tallies[d5 - 1]++;
    for (i = 0; i < 6; i++)
        if (tallies[i] >= 4)
            return (i + 1) * 4;
    return 0;
}

Yatzy.three_of_a_kind = function (d1, d2, d3, d4, d5) {
    var t;
    t = [0, 0, 0, 0, 0, 0, 0, 0, 0]
    t[d1 - 1]++;
    t[d2 - 1]++;
    t[d3 - 1]++;
    t[d4 - 1]++;
    t[d5 - 1]++;
    for (i = 0; i < 6; i++)
        if (t[i] >= 3)
            return (i + 1) * 3;
    return 0;
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


