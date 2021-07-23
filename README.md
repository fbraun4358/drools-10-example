# drools-executable-binding-bug
The executable model is not able to parse a binding to a variable like the DRL model.

A rule like this will fail compiling in the executable model while it will not in the old model:
```
rule "Rule that breaks ExecutableModel"
@filename("rules.drl")
ruleflow-group "RFG-1"
salience 5
    when
        $values: List()
        $cwv:    ClassWithValue( $value: value.charAt(5) == '5' )
    then
        $values.add($value);
end
```
