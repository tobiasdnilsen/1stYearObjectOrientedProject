def convert(lst):
    res = map(lambda i: (lst[i], lst[i+1]), range(len(lst)-1)[::2])
    return dict(res)

val= ["søk", "nå", "på", "sommerjobb", "hos", "kantega", "frist", "1. oktober"]

val= convert (val)

print(val["på"] + "." + val["hos"] + "on ."[::-1])