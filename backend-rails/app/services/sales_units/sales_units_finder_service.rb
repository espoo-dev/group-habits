module SalesUnits
  class SalesUnitsFinderService < FinderService
    def prepare_resource
      SalesUnitPolicy::Scope.new(user, SalesUnit).resolve
    end
  end
end
