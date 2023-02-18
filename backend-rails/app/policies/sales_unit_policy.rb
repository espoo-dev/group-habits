class SalesUnitPolicy < ApplicationPolicy
  class Scope < ApplicationPolicy::Scope
    def resolve
      SalesUnit.all
    end
  end
end
