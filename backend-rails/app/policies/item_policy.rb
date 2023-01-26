class ItemPolicy < ApplicationPolicy
  class Scope < ApplicationPolicy::Scope
    def resolve
      Item.where(user:)
    end
  end
end
